package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepositoryLegacy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepositoryLegacy employeeRepositoryLegacy;

    public EmployeeService(EmployeeRepositoryLegacy employeeRepositoryLegacy) {
        this.employeeRepositoryLegacy = employeeRepositoryLegacy;
    }

    public List<Employee> getAll() {
        return employeeRepositoryLegacy.findAll();
    }

    public Employee createEmployee(Employee employee) {
        employeeRepositoryLegacy.addEmployee(employee);
        return employee;
    }

    public void updateEmployee(int employeeID, Employee updatedEmployee) {
        employeeRepositoryLegacy.updateEmployee(employeeID, updatedEmployee);
    }

    public void deleteEmployee(int employeeID) {
        employeeRepositoryLegacy.deleteEmployee(employeeID);
    }

    public Employee findEmployee(int employeeID) {
        return employeeRepositoryLegacy.findEmployeeByID(employeeID);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepositoryLegacy.findEmployeesByGender(gender);
    }

    public List<Employee> pagination(int page, int pageSize) {
        return employeeRepositoryLegacy.pagination(page, pageSize);
    }
}
