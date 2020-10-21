package com.thoughtworks.springbootemployee.models;

import java.util.List;

public class Company {
    private int companyID;
    private String companyName;
    private int employeeCount;
    private List<Employee> employees;

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
