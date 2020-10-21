package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> findAll() {
        return companies;
    }
}
