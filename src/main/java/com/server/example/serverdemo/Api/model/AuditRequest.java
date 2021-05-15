package com.server.example.serverdemo.Api.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuditRequest {

    private Date created;
    private String createdBy;
    private Date modified;
    private String modifiedBy;

}
