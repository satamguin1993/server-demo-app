package com.server.example.serverdemo.Exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends CftException{

    private static String MESSAGE_FORMAT = "Employee with id %s not found in the system";

    public EmployeeNotFoundException(int employeeId) {
        super(HttpStatus.NOT_FOUND.value(),
                String.format(MESSAGE_FORMAT, employeeId));
    }
}
