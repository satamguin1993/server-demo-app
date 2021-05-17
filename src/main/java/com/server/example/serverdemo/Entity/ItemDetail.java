package com.server.example.serverdemo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private UUID itemUniqueId;

    @Column
    private Integer itemId;

    @Column
    private Integer itemBought;

    @Column
    private float pricePerUnit;

    @Column
    private Integer customerId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "addressId")
    @Fetch(FetchMode.JOIN)
    private Address address;


}
