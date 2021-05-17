package com.server.example.serverdemo.Entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Integer customerId;

    @OneToMany
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<ItemDetail> itemDetails;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Date transactionDate;

    @Column
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private UUID transactionUniqueId;

}
