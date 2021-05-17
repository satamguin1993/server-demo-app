package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Entity.Employee;
import com.server.example.serverdemo.Api.Requests.EmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee mapToEmployeeEntity(EmployeeRequest employeeRequest);

    EmployeeRequest mapToEmployeeRequest(Employee employee);

}
