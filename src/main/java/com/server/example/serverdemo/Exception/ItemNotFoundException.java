package com.server.example.serverdemo.Exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends CftException{

    private static String MESSAGE_FORMAT = "Item with id %s not found in the system";

    public ItemNotFoundException(int itemId) {
        super(HttpStatus.NOT_FOUND.value(),
                String.format(MESSAGE_FORMAT, itemId));
    }

}
