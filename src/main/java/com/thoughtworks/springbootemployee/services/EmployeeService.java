package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(int employeeID, Employee updatedEmployee) {
        Employee employee = findEmployee(employeeID);
        employee.setEmployeeId(updatedEmployee.getEmployeeId());
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setCompanyId(updatedEmployee.getCompanyId());
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(int employeeID) {
        employeeRepository.deleteById(employeeID);
    }

    public Employee findEmployee(int employeeID) {
        return employeeRepository.findById(employeeID).orElse(null);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> pagination(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return employeeRepository.findAll(pageable).toList();
    }
}
