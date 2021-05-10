package com.server.example.serverdemo.Exception;

public class CftException extends RuntimeException{

    private final int statusCode;

    public CftException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
