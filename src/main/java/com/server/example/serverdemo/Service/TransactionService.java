package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Resource.model.TransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionRequest createTransaction(TransactionRequest transactionRequest) {
        return null;
    }

    public TransactionRequest getTransactionRequestById(Integer transactionId) {
        return null;
    }

}
