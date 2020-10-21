package com.thoughtworks.springbootemployee.models;

import java.util.List;

public class Company {
    private int companyID;
    private String companyName;
    private int employeeCount;
    private List<Employee> employees;

    public Company(int companyID, String companyName, int employeeCount, List<Employee> employees) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.employeeCount = employeeCount;
        this.employees = employees;
    }

    public Company() { }

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
