package com.server.example.serverdemo.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Employee {

    public enum Status {
        ACTIVE,
        PENDING,
        INACTIVE,
        ARCHIVED
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private Department department;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Date created;

}
