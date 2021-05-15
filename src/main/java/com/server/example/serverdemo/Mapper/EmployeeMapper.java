package com.server.example.serverdemo.Mapper;

import com.server.example.serverdemo.Model.Employee;
import com.server.example.serverdemo.Api.model.EmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    Employee mapToEmployeeEntity(EmployeeRequest employeeRequest);

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeRequest mapToEmployeeRequest(Employee employee);

}
