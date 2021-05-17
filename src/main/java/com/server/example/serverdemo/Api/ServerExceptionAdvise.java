package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Api.Requests.Error;
import com.server.example.serverdemo.Api.Requests.ErrorItem;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import com.server.example.serverdemo.Exception.TransactionNotFoundException;
import com.server.example.serverdemo.Exception.TransactionValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ServerExceptionAdvise {

    @ExceptionHandler({
            TransactionNotFoundException.class
    })
    public ResponseEntity<Error> handleTransactionNotFoundException(TransactionNotFoundException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({
            TransactionValidationException.class
    })
    public ResponseEntity<List<ErrorItem>> handleTransferValidationException(TransactionValidationException e) {
        return buildValidationErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, e.getErrorDetails());
    }


    private ResponseEntity<Error> buildErrorResponse(String message, HttpStatus httpStatus) {
        Error error = new Error();
        error.setCode(httpStatus.toString());
        error.setMessage(message);
        return ResponseEntity.status(httpStatus).body(error);
    }

    private ResponseEntity<List<ErrorItem>> buildValidationErrorResponse(HttpStatus errorStatus,
                                                                         ValidationResult validationResult) {
        List<ErrorItem> errorItems = new ArrayList<>();
        validationResult.getErrorList().forEach((key, value) -> {
            ErrorItem errorItem = new ErrorItem();
            errorItem.setField(key);
            errorItem.setMessage(value);
            errorItems.add(errorItem);
        });
        return ResponseEntity.status(errorStatus).body(errorItems);
    }

}
