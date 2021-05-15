package com.server.example.serverdemo.Api.model;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private Map<String, String> errorList = new HashMap<>();

    public void setFailed(String fieldName, String failedMessage) {
        errorList.put(fieldName, failedMessage);
    }

    public boolean hasError() {
        return ! errorList.isEmpty();
    }

    public Map<String, String> getErrorList() {
        return errorList;
    }

    public void setErrorList(Map<String, String> errorList) {
        this.errorList = errorList;
    }
}
