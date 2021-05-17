package com.server.example.serverdemo.Api.Requests;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ItemRequest {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Float weight;
    private String metaData;
    private Date publishedDate;
    private Integer availableCopies;
    private Integer soldSoFar;
    private Integer maxUnitAvailablePerCustomer;
    private Department department;
    private ItemStatus status;
    private UUID encodedItemId;
    private Integer brandId;
    private Integer categoryId;

}
