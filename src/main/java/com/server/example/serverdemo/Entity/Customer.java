package com.server.example.serverdemo.Entity;


import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
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

@Entity
@Table
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @Column(nullable = false)
    private String fullName;

    @Column
    private String prefix;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @CollectionTable(joinColumns = @JoinColumn(name = "address"))
    @Fetch(FetchMode.JOIN)
    private List<Address> addressList;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Boolean isActive;

}
