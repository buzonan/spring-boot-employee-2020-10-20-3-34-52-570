package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.CompanyService;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTests {
    @Test
    void should_return_all_companies_when_get_all_companies_given() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Company> expectedCompanyList = asList(new Company(), new Company());
        when(companyRepository.findAll()).thenReturn(expectedCompanyList);
        CompanyService companyService = new CompanyService(companyRepository);

        List<Company> actualCompanies = companyService.getAll();

        assertEquals(expectedCompanyList.size(), actualCompanies.size());
    }

    @Test
    void should_return_companies_when_add_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company expectedCompany = new Company(1,"Tom",employees);
        when(companyRepository.addCompany(expectedCompany)).thenReturn(expectedCompany);
        CompanyService companyService = new CompanyService(companyRepository);

        Company actualCompany = companyService.createCompany(expectedCompany);

        assertEquals(actualCompany.getCompanyID(), actualCompany.getCompanyID());
    }

    @Test
    void should_return_company_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        when(companyRepository.findCompany(company.getCompanyID())).thenReturn(company);
        CompanyService companyService = new CompanyService(companyRepository);

        Company foundCompany = companyService.findCompany(company.getCompanyID());

        assertSame(company, foundCompany);
    }

    @Test
    void should_return_employees_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        when(companyRepository.findEmployeeByCompanyID(company.getCompanyID())).thenReturn(employees);
        CompanyService companyService = new CompanyService(companyRepository);

        List<Employee> foundEmployees = companyService.findEmployeeByCompanyID(company.getCompanyID());

        assertSame(employees.size(), foundEmployees.size());
    }

    @Test
    void should_return_employee_list_when_find_employee_given_page_and_page_size() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        List<Company> companies = asList(
                new Company(1,"Tom",employees),
                new Company(2,"Jerry",employees),
                new Company(3,"Nisa",employees),
                new Company(4,"SQUENIX",employees),
                new Company(5,"Sega",employees));
        when(companyRepository.pagination(1,5)).thenReturn(companies);
        CompanyService companyService = new CompanyService(companyRepository);

        List<Company> pagedCompanies = companyService.pagination(1,5);

        assertSame(companies.size(), pagedCompanies.size());
    }

    @Test
    void should_return_company_when_update_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        Company updatedCompany = new Company(1,"Jerry",employees);

        when(companyRepository.addCompany(company)).thenReturn(updatedCompany);
        CompanyService companyService = new CompanyService(companyRepository);

        companyService.updateCompany(company.getCompanyID(), updatedCompany);

        Mockito.verify(companyRepository).updateCompany(company.getCompanyID(), updatedCompany);
    }

    @Test
    void should_return_when_delete_employees_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        when(companyRepository.addCompany(company)).thenReturn(company);
        CompanyService companyService = new CompanyService(companyRepository);

        companyService.deleteEmployees(company.getCompanyID());

        Mockito.verify(companyRepository).deleteEmployees(company.getCompanyID());
    }
}
