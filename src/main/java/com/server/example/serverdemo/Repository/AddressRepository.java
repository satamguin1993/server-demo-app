package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
