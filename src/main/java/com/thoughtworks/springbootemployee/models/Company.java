package com.thoughtworks.springbootemployee.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String companyName;
    @OneToMany(
            fetch = FetchType.LAZY
    )
    @JoinColumn(columnDefinition = "company_Id")
    private List<Employee> employees;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() { }

    public int getCompanyId() {
        return companyId;
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
