package com.server.example.serverdemo.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class AuditEntity {

    @Column(nullable = false)
    private Date created;
    @Column
    private String createdBy;
    @Column
    private Date modified;
    @Column
    private String modifiedBy;

}
