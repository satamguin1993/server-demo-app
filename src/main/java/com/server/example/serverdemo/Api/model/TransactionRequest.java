package com.server.example.serverdemo.Api.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionRequest extends AuditRequest{

    public enum Status {
        PAID,
        PENDING,
        PAYMENT_FAILED,
        PAYMENT_BY_CREDITS
    }

    public enum PaymentMethod {
        DEBIT_CARD,
        CREDIT_CARD,
        INTERNET_BANKING,
        COD,
        COUPON
    }

    private Integer transactionId;
    private String customerId;
    private List<Integer> itemIds;
    private Status status;
    private Float price;
    private Date transactionDate;
    private PaymentMethod paymentMethod;
    private UUID transactionUniqueId;

}
