package com.thoughtworks.springbootemployee.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyID;
    private String companyName;
    @OneToMany(
        orphanRemoval = true
    )
    @JoinColumn(columnDefinition = "company_id")
    private List<Employee> employees;

    public Company(int companyID, String companyName, List<Employee> employees) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company() { }

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
