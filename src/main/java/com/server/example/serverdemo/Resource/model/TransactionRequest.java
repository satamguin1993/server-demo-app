package com.server.example.serverdemo.Resource.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TransactionRequest {

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

    private Integer customerId;

    private Integer transactionId;

    private List<ItemRequest> items;

    private Status status;

    private Float price;

    private Date transactionDate;

    private PaymentMethod paymentMethod;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
