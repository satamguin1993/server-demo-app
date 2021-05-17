package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AuditRequest {

    @JsonProperty
    private Date created;

    @JsonProperty
    private String createdBy;

    @JsonProperty
    private Date modified;

    @JsonProperty
    private String modifiedBy;

}
