package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequest {

    @JsonProperty
    private Integer customerId;

    @JsonProperty(required = true)
    private String fullName;

    @JsonProperty
    private String prefix;

    @JsonProperty
    private List<AddressRequest> addressList;

    @JsonProperty(required = true)
    private String email;

    @JsonProperty
    private Date created;

    @JsonProperty(required = true)
    private Boolean isActive;

}
