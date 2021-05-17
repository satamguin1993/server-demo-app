package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.ItemDetail;
import com.server.example.serverdemo.Api.Requests.TransactionRequest;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import com.server.example.serverdemo.Entity.Transaction;
import com.server.example.serverdemo.Exception.TransactionNotFoundException;
import com.server.example.serverdemo.Exception.TransactionValidationException;
import com.server.example.serverdemo.Mapper.TransactionMapper;
import com.server.example.serverdemo.Repository.ItemRepository;
import com.server.example.serverdemo.Repository.TransactionRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private ValidateTransactionService validateTransactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    public TransactionRequest createTransaction(TransactionRequest transactionRequest) {

        Optional<ValidationResult> optionalResult =
                validateTransactionService.validateCreateTransactionRequest(transactionRequest);
        if (optionalResult.isPresent()){
            logger.error("Error occurred for creating new transaction request for transactionUniqueId={} customerId={}",
                    transactionRequest.getTransactionUniqueId(),
                    transactionRequest.getCustomerId());
            throw new TransactionValidationException(optionalResult.get());
        } else {
            logger.info("Validation successful for creating new transaction request for transactionUniqueId={} customerId={}",
                    transactionRequest.getTransactionUniqueId(),
                    transactionRequest.getCustomerId());

            transactionRequest.setPrice(calculateTotalPriceAfterDiscount(transactionRequest));
            transactionRequest.setStatus(TransactionRequest.Status.PENDING);
            transactionRequest.setCreated(new Date());
            transactionRequest.setTransactionDate(new Date());
            transactionRequest.setCreatedBy("TO_BE_CHANGED_LATER");
            transactionRequest.setModified(new Date());
            transactionRequest.setModifiedBy(new String());


            Integer customerId = transactionRequest.getCustomerId();
            List<com.server.example.serverdemo.Entity.ItemDetail> itemDetailList =
                    fetchItemDetailList(transactionRequest, customerId);

            itemService.updateItemDetailsBeforeCheckout(transactionRequest, itemDetailList);
            Transaction transaction = TransactionMapper.INSTANCE.mapToTransactionEntity(
                                                transactionRequest, itemDetailList);

            transaction = transactionRepository.save(transaction);
            transactionRequest = TransactionMapper.INSTANCE.mapToTransactionRequest(transaction);
            logger.info("Transaction request is saved successfully in db");
        }

        logger.info("New transaction request created for transactionId={} transactionUniqueId={} customerId={}",
                transactionRequest.getTransactionId(),
                transactionRequest.getTransactionUniqueId(),
                transactionRequest.getCustomerId());

        return transactionRequest;
    }

    private List<com.server.example.serverdemo.Entity.ItemDetail> fetchItemDetailList(
            TransactionRequest request, Integer customerId) {
        return request.getItemDetails().stream()
                .map(itemDetail ->
                        TransactionMapper.INSTANCE.mapToItemDetailEntity(
                                itemDetail, customerId))
                .collect(Collectors.toList());

    }

    private Float calculateTotalPriceAfterDiscount(TransactionRequest request) {

        Float totalPrice = 0F;
        for (int i=0;i<request.getItemDetails().size(); i++) {
            ItemDetail itemDetail = request.getItemDetails().get(i);
            totalPrice += itemDetail.getPricePerUnit() * itemDetail.getItemBought();
        }
        return totalPrice;
    }

    /*private List<Item> fetchAllItemsFromProvidedItemIds(List<ItemDetail> itemDetails) {

        List<Item> items =
                itemDetails.stream().map(
                        itemDetail -> itemRepository.findById(itemDetail.getItemId()))
                        .filter(optionalItem -> optionalItem.isPresent())
                        .map(optionalItem -> optionalItem.get())
                        .collect(Collectors.toList());

        return items;
    }*/

    public TransactionRequest getTransactionRequestById(Integer transactionId) {

        TransactionRequest transactionRequest;
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (transactionOptional.isPresent()) {
            logger.info("Transaction details is found for transactionId={}", transactionId);
            transactionRequest = TransactionMapper.INSTANCE.mapToTransactionRequest(transactionOptional.get());
        } else {
            logger.error("Transaction is not present in the system with transactionid={}", transactionId);
            throw new TransactionNotFoundException(transactionId);
        }
        return transactionRequest;
    }

    public List<TransactionRequest> getAllTransactions() {

        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionRequest> transactionRequests = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transactions)) {
            transactionRequests = transactions.stream().map(
                    transaction -> TransactionMapper.INSTANCE.mapToTransactionRequest(transaction))
                    .collect(Collectors.toList());
        }
        return transactionRequests;
    }
}
