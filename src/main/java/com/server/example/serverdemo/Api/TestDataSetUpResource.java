package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Api.Requests.CustomerRequest;
import com.server.example.serverdemo.Service.CustomerService;
import com.server.example.serverdemo.Service.TestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class TestDataSetUpResource {

    private static final Logger logger = LoggerFactory.getLogger(TestDataSetUpResource.class);

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/v1/testData/")
    public ResponseEntity<Void> setUpTestData() {
        logger.info("Setting up dummy test data");
        testDataService.createTestData();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/v1/testData/customer")
    public ResponseEntity<CustomerRequest> createCustomer(@Validated @RequestBody CustomerRequest customerRequest) {
        logger.info("Creating new customer for name={}", customerRequest.getFullName());
        customerRequest = testDataService.createCustomerRequest(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRequest);
    }

    @GetMapping("/v1/testData/customer/{customerId}")
    public ResponseEntity<CustomerRequest> getCustomer(@PathVariable("customerId") Integer customerId) {
        logger.info("Fetching customer for customerId={}", customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                customerService.fetchCustomerById(customerId));
    }

}
