package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.example.serverdemo.Entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDetail {

    @JsonProperty
    private Integer id;

    @JsonProperty(required = true)
    private Integer itemId;

    @JsonProperty(required = true)
    private Integer itemBought;

    @JsonProperty(required = true)
    private Float pricePerUnit;

    @JsonProperty
    private UUID itemUniqueId;

    @JsonProperty(required = true)
    private Address address;

}
