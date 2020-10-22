package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    public static final String COMPANIES_URI = "/companies";
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    void should_get_all_companies_when_get_all() throws Exception {
        //given
        Company company = new Company("SVG");
        companyRepository.save(company);

        //when
        //then
        mockMvc.perform(get(COMPANIES_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("SVG"));
    }

    @Test
    void should_create_company_when_create_given_company() throws Exception {
        //given
        String companyAsJson = "{\n" +
                "    \"companyName\" : \"SVG\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(post(COMPANIES_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("SVG"));
    }

    @Test
    void should_update_company_when_update_given_company_Id_and_updated_company() throws Exception {
        //given
        String companyAsJson = "{\n" +
                "    \"companyName\" : \"SVG\"\n" +
                "}";

        Company existingCompany = companyRepository.save(new Company("Tata"));

        //when
        //then
        mockMvc.perform(put(COMPANIES_URI + "/" + existingCompany.getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("SVG"));
    }

    @Test
    void should_return_employee_when_get_employees_by_company_given_company() throws Exception {
        //given
        Company company = companyRepository.save(new Company("SVG"));
        Employee employee = employeeRepository.save(new Employee(1, "Tom", 18, "male",1000, company.getCompanyId()));


        //when
        //then
        mockMvc.perform(get(COMPANIES_URI +"/"+company.getCompanyId()+"/employees"))
                .andExpect(jsonPath("$[0].employeeId").isNumber())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()));
    }

    @Test
    void should_return_specific_company_when_get_company_given_company_Id() throws Exception {
        //given
        Company company = companyRepository.save(new Company("SVG"));
        companyRepository.save(new Company("Tata"));

        //when
        //then
        mockMvc.perform(get(COMPANIES_URI +"/"+company.getCompanyId()))
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("SVG"));
    }
}
