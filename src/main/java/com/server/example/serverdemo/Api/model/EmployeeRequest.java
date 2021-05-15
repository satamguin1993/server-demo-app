package com.server.example.serverdemo.Api.model;


import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {

    public enum Status {
        ACTIVE,
        PENDING,
        INACTIVE,
        ARCHIVED
    }

    private int id;
    private String name;
    private Department department;
    private Date created;
    private Status status;

}
