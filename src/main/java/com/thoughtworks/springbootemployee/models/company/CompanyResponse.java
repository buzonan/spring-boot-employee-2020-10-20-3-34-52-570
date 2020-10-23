package com.thoughtworks.springbootemployee.models.company;

import com.thoughtworks.springbootemployee.models.employee.Employee;

import java.util.List;


public class CompanyResponse {
    private int companyId;
    private String companyName;
    private int employeeNumber;
    private List<Employee> employees;

    public CompanyResponse(String companyName) {
        this.companyName = companyName;
    }

    public CompanyResponse() { }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
