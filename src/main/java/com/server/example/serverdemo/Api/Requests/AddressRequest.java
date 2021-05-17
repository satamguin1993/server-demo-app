package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressRequest {

    private Integer addressId;

    @JsonProperty(required = true)
    private String addressLine1;

    @JsonProperty
    private String addressLine2;

    @JsonProperty(required = true)
    private String city;

    @JsonProperty(required = true)
    private String state;

    @JsonProperty(required = true)
    private String pinCode;

    @JsonProperty(required = true)
    private String phoneNumber;

    @JsonProperty(required = true)
    private Boolean isDefault;

}
