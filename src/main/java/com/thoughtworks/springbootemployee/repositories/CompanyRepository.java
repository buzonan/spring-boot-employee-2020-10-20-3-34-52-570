package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }
}
