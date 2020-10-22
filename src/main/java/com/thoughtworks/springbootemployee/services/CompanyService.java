package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return companyRepository.save(company);
    }

    public Company findCompany(int companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> findEmployeeBycompanyId(int companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public List<Company> getCompanyByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return companyRepository.findAll(pageable).toList();
    }

    public Company updateCompany(int companyId, Company updatedCompany) {
        Company company = findCompany(companyId);
        company.setCompanyName(updatedCompany.getCompanyName());
        company.setEmployees(updatedCompany.getEmployees());
        companyRepository.save(company);
        return updatedCompany;
    }

    public void deleteEmployees(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
