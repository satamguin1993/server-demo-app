package com.server.example.serverdemo.Resource;

import com.server.example.serverdemo.Resource.model.EmployeeRequest;
import com.server.example.serverdemo.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@ResponseBody
public class EmployeeResource {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/v1/employees/{employeeId}")
    public ResponseEntity<EmployeeRequest> getEmployeeById(@PathVariable("employeeId") int employeeId,
           @RequestParam(required = false, defaultValue = "false", name="isTest") boolean isTest) {
        logger.info("EmployeeRequest Param or Query Param Id sent from client: " + employeeId);
        logger.info("Path Param Id sent from client: " + employeeId);
        EmployeeRequest employeeRequest = isTest ?
                employeeService.getEmployeeById(employeeId) : employeeService.getTestEmployeeById(employeeId);
        logger.info("Employee Retrieved from the DB for employeeId={}", employeeId);
        return ResponseEntity.ok(employeeRequest);
    }

    @GetMapping("/v1/employees/")
    public ResponseEntity<EmployeeRequest> getAllEmployees(@RequestParam(required = false, name="status") Optional<String> status) {
        EmployeeRequest employeeRequest = employeeService.getAllEmployees(status);
        return ResponseEntity.ok(employeeRequest);
    }

    @PostMapping("/v1/employees/")
    public ResponseEntity<EmployeeRequest> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("Received request for creating new employee");
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @PatchMapping("/v1/employees/{employeeId}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathParam("employeeId") int employeeId,
            @RequestBody EmployeeRequest employeeRequest) {
        logger.info("Received request for updating existing employee with employeeId={}", employeeId);
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeRequest));
    }

}
