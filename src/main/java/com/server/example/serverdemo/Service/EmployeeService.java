package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Exception.EmployeeNotFoundException;
import com.server.example.serverdemo.Exception.EmployeeValidationException;
import com.server.example.serverdemo.Model.Employee;
import com.server.example.serverdemo.Repository.EmployeeRepository;
import com.server.example.serverdemo.Resource.model.EmployeeRequest;
import com.server.example.serverdemo.Resource.model.ValidationResult;
import com.server.example.serverdemo.Mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeValidationService employeeValidationService;

    public EmployeeRequest getTestEmployeeById(int employeeId) {

        System.out.println("Employee id  : " + employeeId);
        List<EmployeeRequest> employees = new ArrayList<>();
        employees.add(createEmployee(1, "abc", "Math"));
        employees.add(createEmployee(2, "xyz", "Science"));

        return createEmployee(2, "xyz", "History");
    }

    private EmployeeRequest createEmployee(int id, String name, String department) {
        EmployeeRequest e = new EmployeeRequest();
        e.setId(id);
        e.setName(name);
        //e.setDepartment(department);
        return e;
    }


    public EmployeeRequest createEmployee(EmployeeRequest employeeRequest) {

        logger.info("Creating new employee in the library");
        ValidationResult validationResult = employeeValidationService.validateEmployeeRequest(employeeRequest);
        if (!validationResult.hasError()) {
            Employee employee = EmployeeMapper.INSTANCE.mapToEmployeeEntity(employeeRequest);
            logger.info("Saving new employee into the database");
            employee = employeeRepository.save(employee);
            logger.info("New employee is registered with employeeId={}", employee.getId());
            employeeRequest = EmployeeMapper.INSTANCE.mapToEmployeeRequest(employee);
            return employeeRequest;
        } else {
            logger.error("Error while validating the EmployeeRequest body");
            throw new EmployeeValidationException(validationResult);
        }
    }

    public EmployeeRequest getEmployeeById(int employeeId) {

        logger.info("Fetching employee with employeeId={}", employeeId);
        Optional<Employee> employeeObj = employeeRepository.findById(employeeId);

        if (employeeObj.isPresent()) {
            logger.info("Employee details retrieved for employeeId={}", employeeId);
            EmployeeRequest employeeRequest = EmployeeMapper.INSTANCE.mapToEmployeeRequest(employeeObj.get());
            return employeeRequest;
        }

        logger.error("Not able to find any employee with employeeId={}", employeeId);
        throw new EmployeeNotFoundException(employeeId);
    }

    public EmployeeRequest getAllEmployees(Optional<String> status) {
        return null;
    }

    public EmployeeRequest updateEmployee(int employeeId, EmployeeRequest request) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        EmployeeRequest employeeRequest;
        if (optionalEmployee.isPresent()) {
            employeeRequest = EmployeeMapper.INSTANCE.mapToEmployeeRequest(optionalEmployee.get());
            if (request.getDepartment() != null) {
                employeeRequest.setDepartment(request.getDepartment());
            }
            if (request.getName() !=null) {
                employeeRequest.setName(request.getName());
            }
            if (request.getStatus() != null) {
                employeeRequest.setStatus(request.getStatus());
            }
            employeeRequest = EmployeeMapper.INSTANCE.mapToEmployeeRequest(
                    employeeRepository.save(
                            EmployeeMapper.INSTANCE.mapToEmployeeEntity(employeeRequest)));
        } else {
            logger.warn("Not able to find any employee with employeeId={}", employeeId);
            throw new EmployeeNotFoundException(employeeId);
        }

        return employeeRequest;
    }
}
