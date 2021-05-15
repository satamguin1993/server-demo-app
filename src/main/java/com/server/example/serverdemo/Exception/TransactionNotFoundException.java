package com.server.example.serverdemo.Exception;

import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends CftException{

    private static String MESSAGE_FORMAT = "Transaction with id %s not found in the system";

    public TransactionNotFoundException(Integer transactionId) {
        super(HttpStatus.NOT_FOUND.value(),
                String.format(MESSAGE_FORMAT, transactionId));
    }


}

