package com.server.example.serverdemo.Api.Requests;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequest {

    public enum Status {
        ACTIVE,
        PENDING,
        INACTIVE,
        ARCHIVED
    }

    @JsonProperty
    private int id;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private Department department;
    @JsonProperty
    private Date created;

    @JsonProperty
    private Status status;

}
