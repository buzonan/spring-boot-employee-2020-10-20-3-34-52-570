package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

public class CompanyServiceTests {
    CompanyRepository companyRepository;
    EmployeeRepository employeeRepository;
    @BeforeEach
    void setUp() {
        companyRepository = Mockito.mock(CompanyRepository.class);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
    }

    @Test
    void should_return_all_companies_when_get_all_companies_given() {
        List<Company> expectedCompanyList = asList(new Company(), new Company());

        when(companyRepository.findAll()).thenReturn(expectedCompanyList);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        List<Company> actualCompanies = companyService.getAll();

        assertEquals(expectedCompanyList.size(), actualCompanies.size());
    }

    @Test
    void should_return_companies_when_add_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company expectedCompany = new Company(1,"Tom",employees);
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company actualCompany = companyService.createCompany(expectedCompany);

        assertEquals(actualCompany.getCompanyID(), actualCompany.getCompanyID());
    }

    @Test
    void should_return_company_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        when(companyRepository.findById(company.getCompanyID()).orElse(null)).thenReturn(company);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company foundCompany = companyService.findCompany(company.getCompanyID());

        assertSame(company, foundCompany);
    }

    @Test
    void should_return_employees_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        when(employeeRepository.findByCompanyId(company.getCompanyID())).thenReturn(employees);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        List<Employee> actual = companyService.findEmployeeBycompanyId(company.getCompanyID());

        assertSame(employees.size(), actual.size());
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

        when(companyRepository.findAll(PageRequest.of(1,5)).toList()).thenReturn(companies);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        List<Company> actual = companyService.getCompanyByPage(1, 5);

        assertSame(companies.size(), actual.size());
    }

    @Test
    void should_return_company_when_update_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        Company updatedCompany = new Company(1,"Jerry",employees);

        when(companyRepository.save(company)).thenReturn(updatedCompany);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company actual = companyService.updateCompany(company.getCompanyID(), updatedCompany);

        assertEquals(updatedCompany.getCompanyID(), actual.getCompanyID());
    }

    @Test
    void should_return_when_delete_employees_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company(1,"Tom",employees);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        companyService.deleteEmployees(company.getCompanyID());

        Mockito.verify(companyRepository).deleteById(company.getCompanyID());
    }
}
