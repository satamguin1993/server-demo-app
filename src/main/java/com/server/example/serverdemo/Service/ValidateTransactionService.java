package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.ItemDetail;
import com.server.example.serverdemo.Api.Requests.TransactionRequest;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import com.server.example.serverdemo.Entity.Item;
import com.server.example.serverdemo.Repository.ItemRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValidateTransactionService {

    private static  final Logger logger = LoggerFactory.getLogger(ValidateTransactionService.class);

    @Autowired
    private ItemValidationService itemValidationService;

    @Autowired
    private ItemRepository itemRepository;

    public Optional<ValidationResult> validateCreateTransactionRequest(TransactionRequest request) {
        ValidationResult result = new ValidationResult();
        if(CollectionUtils.isEmpty(request.getItemDetails())) {
            result.setFailed("items", "items list is empty");
        } if(request.getCustomerId() == null) {
            result.setFailed("customerId", "customerId must not be null or empty");
        }
        if (result.hasError()) {
            return Optional.of(result);
        } else {
            List<ItemDetail> invalidItems =
                    request.getItemDetails().stream()
                            .filter(itemDetail ->
                                    validateEachItemById(itemDetail.getItemId()))
                            .collect(Collectors.toList());
            StringBuffer sb = new StringBuffer();
            invalidItems.stream().forEach(itemDetail -> sb.append(itemDetail.getItemId() + " "));
            logger.error("Provided itemIds are not present in the system itemIds={}", sb.toString());
            result.setFailed("itemIds", "Below itemIds are invalid " + sb.toString());
        }
        return Optional.empty();
    }

    private boolean validateEachItemById(Integer itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent())
            return false;
        return true;
    }
}
