package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
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
        employeeRepository.addEmployee(employee);
        return employee;
    }

    public void updateEmployee(int employeeID, Employee updatedEmployee) {
        employeeRepository.updateEmployee(employeeID, updatedEmployee);
    }

    public void deleteEmployee(int employeeID) {
        employeeRepository.deleteEmployee(employeeID);
    }

    public Employee findEmployee(int employeeID) {
        return employeeRepository.findEmployeeByID(employeeID);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findEmployeesByGender(gender);
    }

    public List<Employee> pagination(int page, int pageSize) {
        return employeeRepository.pagination(page, pageSize);
    }
}
