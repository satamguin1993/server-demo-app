package com.server.example.serverdemo.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class Item {

    public enum Status {
        AVAILABLE,
        SOLD_OUT,
        PENDING,
        COMING_SOON
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float price;

    @Column
    private Float weight;

    @Column
    private String metaData;

    @Column
    private Date publishedDate;

    @Column
    private Integer availableCopies;

    @Column
    private Integer soldSoFar;

    @Column
    private Integer maxUnitAvailablePerCustomer;

    @Column
    private Department department;

    @Column
    private Status status;

    @Column
    private UUID encodedItemId;

    /*@OneToOne
    @JoinColumn(name = "id", nullable = false)*/
    @Column
    private Integer brandId;

    /*@OneToOne
    @JoinColumn(name = "id")*/
    @Column
    private  Integer categoryId;
}
