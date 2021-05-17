package com.server.example.serverdemo.Exception;

import com.server.example.serverdemo.Api.Requests.ValidationResult;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TransactionValidationException extends CftException{

    private ValidationResult errorDetails;

    public TransactionValidationException(ValidationResult validationResult) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error : " + validationResult.getErrorList().toString());

        errorDetails = validationResult;
    }

    public ValidationResult getErrorDetails() {
        return errorDetails;
    }
}
