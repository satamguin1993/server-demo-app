package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.ItemRequest;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class ItemValidationService {

    public ValidationResult validateCreateItemRequest(ItemRequest itemRequest) {
        ValidationResult validationResult = new ValidationResult();
        return validationResult;
    }
}
