package com.server.example.serverdemo.Exception;

import com.server.example.serverdemo.Api.Requests.ValidationResult;
import org.springframework.http.HttpStatus;

public class ItemValidationException extends CftException{

    private ValidationResult errorDetails;

    public ItemValidationException(ValidationResult validationResult) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error : " + validationResult.getErrorList().toString());

        errorDetails = validationResult;
    }

}
