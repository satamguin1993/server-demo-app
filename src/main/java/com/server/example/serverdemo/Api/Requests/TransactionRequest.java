package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonProperty(value = "transactionId")
    private Integer transactionId;

    @JsonProperty(value = "transactionUniqueId")
    private UUID transactionUniqueId;

    @JsonProperty(required = true)
    private Integer customerId;

    @JsonProperty(required = true)
    private List<ItemDetail> itemDetails;

    @JsonProperty
    private Status status;

    @JsonProperty
    private Float price;

    @JsonProperty
    private Date transactionDate;

    @JsonProperty(required = true)
    private PaymentMethod paymentMethod;

}
