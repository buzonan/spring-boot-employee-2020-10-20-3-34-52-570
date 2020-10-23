package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.InvalidCompanyException;
import com.thoughtworks.springbootemployee.models.company.Company;
import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getAll() { return companyRepository.findAll();
    }

    public Company createCompany(Company company) {
        validateCompany(company);
        return companyRepository.save(company);
    }

    public Company findCompany(int companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(()-> new CompanyNotFoundException("Company Id "+ companyId +" Not Found"));
    }

    public List<Employee> findEmployeeByCompanyId(int companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public List<Company> getCompanyByPage(int page, int pageSize) {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(page, pageSize));
        return companies.toList();
    }

    public Company updateCompany(int companyId, Company newCompany) {
        validateCompany(newCompany);
        Company company = findCompany(companyId);
        company.setCompanyName(newCompany.getCompanyName());
        company.setEmployees(newCompany.getEmployees());
        companyRepository.save(company);
        return company;
    }

    private void validateCompany(Company company) {
        if(isNull(company.getCompanyName())){
            throw new InvalidCompanyException("Invalid Company");
        }
    }

    public void deleteEmployees(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
