package com.server.example.serverdemo.Repository;


import com.server.example.serverdemo.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
