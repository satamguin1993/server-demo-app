package com.server.example.serverdemo.Resource;

import com.server.example.serverdemo.Resource.model.TransactionRequest;
import com.server.example.serverdemo.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@ResponseBody
public class TransactionResource {

    private static final Logger logger = LoggerFactory.getLogger(TransactionResource.class);

    @Autowired
    TransactionService transactionService;

    @PostMapping("/v1/transaction/")
    public ResponseEntity<TransactionRequest> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        logger.info("Received request for creating new transaction for customerId={}", transactionRequest.getCustomerId());
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequest));
    }

    @GetMapping("/v1/transaction/{transactionId}")
    public ResponseEntity<TransactionRequest> getTransactionRequestById(@PathParam("transactionId") Integer transactionId) {
        logger.info("Received request for fetching transaction for transactionId={}", transactionId);
        return ResponseEntity.ok(transactionService.getTransactionRequestById(transactionId));
    }

}
