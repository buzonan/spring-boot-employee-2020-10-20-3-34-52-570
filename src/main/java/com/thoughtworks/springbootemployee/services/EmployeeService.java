package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.exception.InvalidEmployeeException;
import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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
        validateEmployee(employee);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(int employeeID, Employee updatedEmployee) {
        validateEmployee(updatedEmployee);
        Employee employee = findEmployee(employeeID);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setCompanyId(updatedEmployee.getCompanyId());
        employeeRepository.save(employee);
        return employee;
    }

    private void validateEmployee(Employee employee) {
        if(isNull(employee.getGender()) || isNull(employee.getName())){
            throw new InvalidEmployeeException("Invalid Employee");
        }
    }

    public void deleteEmployee(int employeeID) {
        employeeRepository.deleteById(employeeID);
    }

    public Employee findEmployee(int employeeID){
        return employeeRepository.findById(employeeID)
                .orElseThrow(()->new EmployeeNotFoundException("Employee Id "+ employeeID +" Not Found"));
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page, pageSize));
        return employees.toList();
    }
}
