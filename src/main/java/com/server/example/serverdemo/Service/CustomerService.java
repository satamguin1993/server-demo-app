package com.server.example.serverdemo.Service;

import com.server.example.serverdemo.Api.Requests.CustomerRequest;
import com.server.example.serverdemo.Entity.Customer;
import com.server.example.serverdemo.Mapper.CustomerMapper;
import com.server.example.serverdemo.Repository.CustomerRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerRequest fetchCustomerById(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
             return CustomerMapper.INSTANCE.mapToCustomerRequest(
                    optionalCustomer.get());
        }
        throw new RuntimeException();
    }

}
