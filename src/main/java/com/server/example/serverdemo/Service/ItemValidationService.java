package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Resource.model.ItemRequest;
import com.server.example.serverdemo.Resource.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class ItemValidationService {

    public ValidationResult validateCreateItemRequest(ItemRequest itemRequest) {
        ValidationResult validationResult = new ValidationResult();
        return validationResult;
    }

}
