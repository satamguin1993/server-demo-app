package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
