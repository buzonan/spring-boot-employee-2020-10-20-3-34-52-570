package com.thoughtworks.springbootemployee.repositories.legacy;

import com.thoughtworks.springbootemployee.models.company.Company;
import com.thoughtworks.springbootemployee.models.employee.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CompanyRepositoryLegacy {
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
                .filter(company -> company.getCompanyId() == companyID)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findEmployeeByCompanyID(int companyID) {
        return Objects.requireNonNull(companies.stream()
                .filter(company -> company.getCompanyId() == companyID)
                .findFirst()
                .orElse(null))
                .getEmployees();
    }

    public List<Company> pagination(int page, int pageSize) {
        int pageSkip = (page-1) * pageSize;
        return companies.stream()
                .sorted(Comparator.comparing(Company::getCompanyId))
                .skip(pageSkip)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void updateCompany(int companyID, Company updatedCompany) {
        companies.stream()
                .filter(company -> company.getCompanyId() == companyID)
                .findFirst()
                .ifPresent(company -> {
                    companies.remove(company);
                    companies.add(updatedCompany);
                });
    }

    public void deleteEmployees(int companyID) {
        companies.stream()
                .filter(company -> company.getCompanyId() == companyID)
                .findFirst()
                .ifPresent(company -> company.getEmployees().clear());
    }
}
