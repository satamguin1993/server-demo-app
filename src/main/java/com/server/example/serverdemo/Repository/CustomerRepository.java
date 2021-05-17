package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
