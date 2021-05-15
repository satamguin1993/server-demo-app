package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Exception.TransactionNotFoundException;
import com.server.example.serverdemo.Exception.TransactionValidationException;
import com.server.example.serverdemo.Mapper.TransactionMapper;
import com.server.example.serverdemo.Model.Item;
import com.server.example.serverdemo.Model.Transaction;
import com.server.example.serverdemo.Repository.ItemRepository;
import com.server.example.serverdemo.Repository.TransactionRepository;
import com.server.example.serverdemo.Api.model.TransactionRequest;
import com.server.example.serverdemo.Api.model.ValidationResult;
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
            updateItemDetailsForCheckout(transactionRequest);
            transactionRequest.setStatus(TransactionRequest.Status.PENDING);
            transactionRequest.setCreated(new Date());
            transactionRequest.setTransactionDate(new Date());
            List<Item> items = fetchAllItemsFromProvidedItemIds(transactionRequest.getItemIds());
            Transaction transaction = TransactionMapper.INSTANCE.mapToTransactionEntity(transactionRequest, items);
            transactionRequest = TransactionMapper.INSTANCE.mapToTransactionRequest(
                    transactionRepository.save(transaction));
            logger.info("Transaction request is saved successfully in db");
            linkCustomerWithItemDetailsAfterCheckout(transactionRequest);
        }

        logger.info("New transaction request created for transactionId={} transactionUniqueId={} customerId={}",
                transactionRequest.getTransactionId(),
                transactionRequest.getTransactionUniqueId(),
                transactionRequest.getCustomerId());

        return transactionRequest;
    }

    private List<Item> fetchAllItemsFromProvidedItemIds(List<Integer> itemIds) {

        List<Item> items =
                itemIds.stream().map(
                        integer -> itemRepository.findById(integer))
                        .filter(optionalItem -> optionalItem.isPresent())
                        .map(optionalItem -> optionalItem.get())
                        .collect(Collectors.toList());

        return items;
    }

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


    //TODO complete this method logic
    private void linkCustomerWithItemDetailsAfterCheckout(TransactionRequest request) {
        logger.info("Link customer with items for transactionId={} customerId={}",
                request.getTransactionId(), request.getCustomerId());
    }


    //TODO complete this method logic
    private void updateItemDetailsForCheckout(TransactionRequest request) {
        logger.info("Update item details for checkout for transactionId={} customerId={}",
                request.getTransactionId(), request.getCustomerId());

    }
}
