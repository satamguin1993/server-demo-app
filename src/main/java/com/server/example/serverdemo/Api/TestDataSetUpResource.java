package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Service.TestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDataSetUpResource {

    private static final Logger logger = LoggerFactory.getLogger(TestDataSetUpResource.class);

    @Autowired
    private TestDataService testDataService;

    @PostMapping("/v1/testData/")
    public ResponseEntity<Void> setUpTestData() {
        logger.info("Setting up dummy test data");
        testDataService.createTestData();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
