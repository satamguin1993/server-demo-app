package com.server.example.serverdemo.Model;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Transaction extends AuditEntity{

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(nullable = false)
    private String customerId;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private List<Item> itemList;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Date transactionDate;

    @Column
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private UUID transactionUniqueId;

}
