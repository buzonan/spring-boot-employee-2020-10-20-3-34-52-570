package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> findAll() {
        return companies;
    }

    public Company addCompany(Company expectedCompany) {
        companies.add(expectedCompany);
        return expectedCompany;
    }

    public Company findCompany(int companyID) {
        return companies.stream()
                .filter(company -> company.getCompanyID() == companyID)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployeeByCompanyID(int companyID) {
        return Objects.requireNonNull(companies.stream()
                .filter(company -> company.getCompanyID() == companyID)
                .findFirst()
                .orElse(null))
                .getEmployees();
    }
}
