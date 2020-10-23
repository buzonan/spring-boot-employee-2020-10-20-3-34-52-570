package com.thoughtworks.springbootemployee.repositories.legacy;

import com.thoughtworks.springbootemployee.models.employee.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryLegacy {
    private final List<Employee> employeeList = new ArrayList<>();

    public List<Employee> findAll() {
        return employeeList;
    }

    public Employee addEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    public void updateEmployee(int employeeID, Employee updatedEmployee) {
        employeeList.stream()
                .filter(employee -> employee.getEmployeeId() == employeeID)
                .findFirst()
                .ifPresent(employee -> {
                    employeeList.remove(employee);
                    employeeList.add(updatedEmployee);
                });
    }

    public void deleteEmployee(int employeeID) {
        employeeList.stream()
                .filter(employee -> employee.getEmployeeId() == employeeID)
                .findFirst()
                .ifPresent(employeeList::remove);
    }

    public Employee findEmployeeByID(int employeeID) {
        return employeeList.stream()
                .filter(employee -> employee.getEmployeeId() == employeeID)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeList.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> pagination(int page, int pageSize) {
        int pageSkip = (page-1) * pageSize;
        return employeeList.stream()
                .skip(pageSkip)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
