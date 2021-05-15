package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.model.TransactionRequest;
import com.server.example.serverdemo.Api.model.ValidationResult;
import com.server.example.serverdemo.Model.Item;
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
        if(CollectionUtils.isEmpty(request.getItemIds())) {
            result.setFailed("items", "items list is empty");
        } if(StringUtils.isEmpty(request.getCustomerId())) {
            result.setFailed("customerId", "customerId must not be null or empty");
        } if(request.getPrice() == null || request.getPrice() < 0f) {
            result.setFailed("price", "price provided in the request is invalid");
        }
        if (result.hasError()) {
            return Optional.of(result);
        } else {
            List<Integer> invalidItemIds =
                    request.getItemIds().stream().filter(itemId -> validateEachItemById(itemId))
                            .collect(Collectors.toList());
            StringBuffer stringBuffer = new StringBuffer();
            invalidItemIds.stream().forEach(integer -> stringBuffer.append(integer + " "));
            logger.error("Provided itemIds are not present in the system itemIds={}",
                    stringBuffer.toString());
            result.setFailed("itemIds", "Below itemIds are invalid " + stringBuffer.toString());
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
