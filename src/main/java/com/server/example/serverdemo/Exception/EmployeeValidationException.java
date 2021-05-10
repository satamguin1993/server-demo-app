package com.server.example.serverdemo.Exception;

import com.server.example.serverdemo.Resource.model.ValidationResult;
import org.springframework.http.HttpStatus;

public class EmployeeValidationException extends CftException{

    private ValidationResult errorDetails;

    public EmployeeValidationException(ValidationResult validationResult) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error : " + validationResult.getErrorList().toString());

        errorDetails = validationResult;
    }
}
