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
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void should_return_compnaies_when_add_company_given_new_company() {
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        List<Employee> employees = new ArrayList<>();
        Company expectedCompany = new Company(1,"Tom",0,employees);
        when(companyRepository.addCompany(expectedCompany)).thenReturn(expectedCompany);
        CompanyService companyService = new CompanyService(companyRepository);

        Company actualCompany = companyService.createCompany(expectedCompany);

        assertEquals(actualCompany.getCompanyID(), actualCompany.getCompanyID());
    }
}
