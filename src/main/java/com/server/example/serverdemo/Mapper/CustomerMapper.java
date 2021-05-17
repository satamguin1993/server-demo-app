package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Api.Requests.CustomerRequest;
import com.server.example.serverdemo.Entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapToCustomerEntity(CustomerRequest customerRequest);

    CustomerRequest mapToCustomerRequest(Customer customer);

}
