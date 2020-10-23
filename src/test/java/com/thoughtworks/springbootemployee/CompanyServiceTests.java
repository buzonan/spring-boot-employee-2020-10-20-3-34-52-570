package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        Company expectedCompany = new Company("Tom");
        when(companyRepository.save(expectedCompany)).thenReturn(expectedCompany);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company actualCompany = companyService.createCompany(expectedCompany);

        assertEquals(actualCompany.getCompanyId(), actualCompany.getCompanyId());
    }

    @Test
    void should_return_company_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        Company company = new Company("Tom");
        when(companyRepository.findById(company.getCompanyId())).thenReturn(java.util.Optional.of(company));
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company foundCompany = companyService.findCompany(company.getCompanyId());

        assertSame(company, foundCompany);
    }

    @Test
    void should_return_employees_when_get_company_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company("Tom");
        when(employeeRepository.findByCompanyId(company.getCompanyId())).thenReturn(employees);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        List<Employee> actual = companyService.findEmployeeByCompanyId(company.getCompanyId());

        assertSame(employees.size(), actual.size());
    }

    @Test
    void should_return_employee_list_when_find_employee_given_page_and_page_size() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        List<Company> companies = asList(
                new Company("Tom"),
                new Company("Jerry"),
                new Company("Nisa"),
                new Company("SQUENIX"),
                new Company("Sega"));

        Pageable pageable = PageRequest.of(1,5);
        Page<Company> companyPage = new PageImpl<>(companies);
        when(companyRepository.findAll(pageable)).thenReturn(companyPage);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        List<Company> actual = companyService.getCompanyByPage(1, 5);

        assertSame(companies.size(), actual.size());
    }

    @Test
    void should_return_company_when_update_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company("Tom");
        Company updatedCompany = new Company("Jerry");

        when(companyRepository.findById(company.getCompanyId())).thenReturn(java.util.Optional.of(updatedCompany));
        when(companyRepository.save(company)).thenReturn(updatedCompany);
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        Company actual = companyService.updateCompany(company.getCompanyId(), updatedCompany);

        assertEquals(updatedCompany.getCompanyId(), actual.getCompanyId());
    }

    @Test
    void should_return_when_delete_employees_given_company_id() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company company = new Company("Tom");
        CompanyService companyService = new CompanyService(companyRepository, employeeRepository);

        companyService.deleteEmployees(company.getCompanyId());

        Mockito.verify(companyRepository).deleteById(company.getCompanyId());
    }
}
