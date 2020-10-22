package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {


    public static final String EMPLOYEES_URI = "/employees";
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_get_all_employees_when_get_all() throws Exception {
        //given
        Employee employee = new Employee(1, "Tom", 45, "male", 140000);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get(EMPLOYEES_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").isNumber())
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(45))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(140000));
    }

    @Test
    void should_create_employee_when_create_given_employee_request() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "\"name\": \"Chels\",\n" +
                "\"age\": 18,\n" +
                "\"gender\": \"female\",\n" +
                "\"salary\": 1000,\n" +
                "\"companyId\": 0\n" +
                "}";

        //when
        //then
        mockMvc.perform(post(EMPLOYEES_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId").isNumber())
                .andExpect(jsonPath("$.name").value("Chels"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(1000))
                .andExpect(jsonPath("$.companyId").value(0));

        List<Employee> employee = employeeRepository.findAll();
        Assertions.assertEquals(1, employee.size());
        Assertions.assertEquals("Chels", employee.get(0).getName());
    }

    @Test
    void should_update_employee_when_update_given_employeeId_and_updatedEmployee() throws Exception {
        //given
        String updatedEmployee = "{\n" +
                "\"name\": \"Chelsie\",\n" +
                "\"age\": 18,\n" +
                "\"gender\": \"female\",\n" +
                "\"salary\": 1000,\n" +
                "\"companyId\": 0\n" +
                "}";

        //when
        Employee employee = employeeRepository.save(new Employee(2, "Chels", 18, "female",  1000));

        //then
        mockMvc.perform(put(EMPLOYEES_URI + "/" + employee.getEmployeeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedEmployee))
                .andExpect(jsonPath("$.employeeId").isNumber())
                .andExpect(jsonPath("$.name").value("Chelsie"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(1000))
                .andExpect(jsonPath("$.companyId").value(0));

        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertEquals(1, employeeList.size());
        Assertions.assertEquals("Chelsie", employeeList.get(0).getName());
    }

    @Test
    void should_return_employee_when_get_employee_given_employee_Id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(3, "Chels", 18, "female",  1000));
        employeeRepository.save(new Employee(4, "Chelsie", 181, "male",  1000));

        //when
        //then
        mockMvc.perform(get(EMPLOYEES_URI +"/"+employee.getEmployeeId()))
                .andExpect(jsonPath("$.employeeId").isNumber())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()));

        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals("Chels", employeeList.get(0).getName());
    }

    @Test
    void should_return_all_male_employees_when_get_employee_given_gender_male() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(5, "Tom", 22, "male",  5000));
        employeeRepository.save(new Employee(6, "Alice", 18, "female",  1000));

        //when
        //then
        mockMvc.perform(get(EMPLOYEES_URI +"?gender="+employee.getGender()))
                .andExpect(jsonPath("$[0].employeeId").isNumber())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()));

        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals("male", employeeList.get(0).getGender());
    }

    @Test
    void should_return_all_female_employees_when_get_employee_given_gender_female() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(7, "Alice", 22, "female",  5000));
        employeeRepository.save(new Employee(8, "Tom", 18, "male",  1000));

        //when
        //then
        mockMvc.perform(get(EMPLOYEES_URI +"?gender="+employee.getGender()))
                .andExpect(jsonPath("$[0].employeeId").isNumber())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()));

        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals("female", employeeList.get(0).getGender());
    }

    @Test
    void should_delete_employee_when_delete_employee_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee(9, "Alice", 22, "female",  5000));
        employeeRepository.save(new Employee(10, "Tom", 18, "male",  1000));

        //when
        mockMvc.perform(delete(EMPLOYEES_URI + "/" + employee.getEmployeeId()));

        //then
        List<Employee> employeeList = employeeRepository.findAll();
        Assertions.assertEquals(1, employeeList.size());
    }

    @Test
    void should_return_employees_page_when_get_employee_given_page_1_pageSize_2() throws Exception {
        //given
        employeeRepository.save(new Employee(1, "Alice1", 21, "female",  5000));
        employeeRepository.save(new Employee(2, "Alice2", 22, "female",  5000));
        employeeRepository.save(new Employee(3, "Alice3", 23, "female",  5000));
        employeeRepository.save(new Employee(4, "Alice4", 24, "female",  5000));
        employeeRepository.save(new Employee(5, "Alice5", 25, "female",  5000));

        int page = 1, pageSize = 2;

        //when
        //then
        mockMvc.perform(get(EMPLOYEES_URI + "?page="+page+"&pageSize="+pageSize))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice3"));
    }
}
