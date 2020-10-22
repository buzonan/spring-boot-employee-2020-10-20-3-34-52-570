package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company createCompany(Company expectedCompany) {
        return updateEmployeeCount(expectedCompany);
    }

    public Company findCompany(int companyID) {
        return updateEmployeeCount(companyRepository.findCompany(companyID));
    }

    public List<Employee> findEmployeeByCompanyID(int companyID) {
        return companyRepository.findEmployeeByCompanyID(companyID);
    }

    public List<Company> pagination(int page, int pageSize) {
        return updateEmployeeCounts(companyRepository.pagination(page, pageSize));
    }

    public void updateCompany(int companyID, Company updatedCompany) {
        updatedCompany.updateEmployeeCount();
        companyRepository.updateCompany(companyID,updatedCompany);
    }

    public void deleteEmployees(int companyID) {
        companyRepository.deleteEmployees(companyID);
    }

    private List<Company> updateEmployeeCounts(List<Company> companies){
        companies.forEach(Company::updateEmployeeCount);
        return companies;
    }

    private Company updateEmployeeCount(Company company){
        company.updateEmployeeCount();
        companyRepository.addCompany(company);
        return company;
    }
}
