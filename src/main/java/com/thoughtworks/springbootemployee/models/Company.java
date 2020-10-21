package com.thoughtworks.springbootemployee.models;

import java.util.List;

public class Company {
    private int companyID;
    private String companyName;
    private int employeeCount;
    private List<Employee> employees;

    public Company(int companyID, String companyName, List<Employee> employees) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.employees = employees;
        updateEmployeeCount();
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

    public void updateEmployeeCount(){ employeeCount = (employees!=null ? employees.size() : 0); }

    public List<Employee> getEmployees() {
        return employees;
    }
}
