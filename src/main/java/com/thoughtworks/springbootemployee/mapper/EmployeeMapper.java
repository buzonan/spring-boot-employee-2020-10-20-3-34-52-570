package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.models.employee.EmployeeRequest;
import com.thoughtworks.springbootemployee.models.employee.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse response = new EmployeeResponse();

        response.setEmployeeId(employee.getEmployeeId());
        response.setName(employee.getName());
        response.setAge(employee.getAge());
        response.setGender(employee.getGender());
        response.setSalary(employee.getSalary());
        response.setCompanyId(employee.getCompanyId());

        return response;
    }

    public Employee toEntity(EmployeeRequest request){
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setGender(request.getGender());
        employee.setSalary(request.getSalary());
        employee.setCompanyId(request.getCompanyId());

        return employee;
    }

}
