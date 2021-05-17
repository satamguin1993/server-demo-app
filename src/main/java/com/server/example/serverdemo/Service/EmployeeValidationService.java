package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.EmployeeRequest;
import com.server.example.serverdemo.Api.Requests.ValidationResult;
import org.springframework.stereotype.Service;

@Service
public class EmployeeValidationService {

    public ValidationResult validateEmployeeRequest(EmployeeRequest employeeRequest) {
        return null;
    }

}
