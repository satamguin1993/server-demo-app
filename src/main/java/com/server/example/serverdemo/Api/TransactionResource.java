package com.server.example.serverdemo.Api;

import com.server.example.serverdemo.Api.Requests.TransactionRequest;
import com.server.example.serverdemo.Exception.TransactionNotFoundException;
import com.server.example.serverdemo.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@Validated
public class TransactionResource {

    private static final Logger logger = LoggerFactory.getLogger(TransactionResource.class);

    @Autowired
    TransactionService transactionService;

    @PostMapping("/v1/transaction/")
    public ResponseEntity<TransactionRequest> createTransaction(
            @RequestBody TransactionRequest transactionRequest) {

        UUID transactionUniqueId = UUID.randomUUID();
        transactionRequest.setTransactionUniqueId(transactionUniqueId);
        logger.info("Received request for creating new transaction for transactionId={} customerId={}",
                transactionUniqueId, transactionRequest.getCustomerId());
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequest));
    }

    @GetMapping("/v1/transaction/{transactionId}")
    public ResponseEntity<TransactionRequest> getTransactionRequestById(
            @PathVariable(value = "transactionId") Integer transactionId) {
        if (transactionId == null) {
            logger.error("transactionId received is null");
            throw new TransactionNotFoundException(null);
        }
        logger.info("Received request for fetching transaction for transactionId={}", transactionId);
        return ResponseEntity.ok(transactionService.getTransactionRequestById(transactionId));
    }

    @GetMapping("/v1/transaction/")
    public ResponseEntity<List<TransactionRequest>> getAllTransactions() {
        logger.info("Received request for getting all the transactions");
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

}
