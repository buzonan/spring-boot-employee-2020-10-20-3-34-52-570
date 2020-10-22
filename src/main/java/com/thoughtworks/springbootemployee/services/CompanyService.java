package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Company createCompany(Company companyResponseBody) {
        Company company = new Company(companyResponseBody.getCompanyName());
        Company savedCompany = companyRepository.save(company);
        int savedCompanyId = savedCompany.getCompanyId();
        companyResponseBody.getEmployees()
                .stream()
                .filter(employee -> !employeeRepository.findById(employee.getEmployeeId()).isPresent())
                .forEach(employee -> {
                        employee.setCompanyId(savedCompanyId);
                        employeeRepository.save(employee);
                });
        return companyRepository.findById(savedCompanyId).orElse(null);
    }

    public Company findCompany(int companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> findEmployeeByCompanyId(int companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public List<Company> getCompanyByPage(int page, int pageSize) {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(page, pageSize));
        return companies.toList();
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
