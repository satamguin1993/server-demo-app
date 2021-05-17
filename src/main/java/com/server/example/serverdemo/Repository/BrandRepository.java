package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Entity.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
}
