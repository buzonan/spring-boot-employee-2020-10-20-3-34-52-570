package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.repositories.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    public static final String COMPANIES_URI = "/companies";

    @Autowired
    private CompanyRepository companyRepository;

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
}
