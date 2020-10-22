package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepositoryLegacy;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTests {
    @Test
    void should_return_all_employees_when_get_all_employees_given() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        List<Employee> expectedEmployeeList = asList(new Employee(), new Employee());
        when(employeeRepositoryLegacy.findAll()).thenReturn(expectedEmployeeList);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        List<Employee> actualEmployees = employeeService.getAll();

        assertEquals(expectedEmployeeList.size(), actualEmployees.size());
    }

    @Test
    void should_return_employee_when_add_employee_given_new_employee() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        Employee expectedEmployee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepositoryLegacy.addEmployee(expectedEmployee)).thenReturn(expectedEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        Employee actualEmployee = employeeService.createEmployee(expectedEmployee);

        assertEquals(expectedEmployee.getEmployeeId(), actualEmployee.getEmployeeId());
    }

    @Test
    void should_return_employee_when_update_employee_given_new_employee() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        Employee updatedEmployee = new Employee(1,"Tom",18,"male",12000);

        when(employeeRepositoryLegacy.addEmployee(employee)).thenReturn(updatedEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        employeeService.updateEmployee(employee.getEmployeeId(), updatedEmployee);

        Mockito.verify(employeeRepositoryLegacy).updateEmployee(employee.getEmployeeId(), updatedEmployee);
    }

    @Test
    void should_return_when_delete_employee_given_employee_id() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepositoryLegacy.addEmployee(employee)).thenReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        employeeService.deleteEmployee(employee.getEmployeeId());

        Mockito.verify(employeeRepositoryLegacy).deleteEmployee(employee.getEmployeeId());
    }

    @Test
    void should_return_employee_when_find_employee_given_employee_id() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepositoryLegacy.findEmployeeByID(employee.getEmployeeId())).thenReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        Employee foundEmployee = employeeService.findEmployee(employee.getEmployeeId());

        assertSame(employee, foundEmployee);
    }

    @Test
    void should_return_employee_list_when_find_employee_given_employee_gender() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        List<Employee> employees =
                Arrays.asList(
                        new Employee(1,"Tom",18,"male",10000),
                        new Employee(2,"Toots",18,"male",10000)
                );
        when(employeeRepositoryLegacy.findEmployeesByGender("male")).thenReturn(employees);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        List<Employee> foundEmployees = employeeService.findEmployeesByGender("male");

        assertSame(employees.size(), foundEmployees.size());
    }

    @Test
    void should_return_employee_list_when_find_employee_given_page_and_page_size() {
        EmployeeRepositoryLegacy employeeRepositoryLegacy = Mockito.mock(EmployeeRepositoryLegacy.class);
        List<Employee> employees =
                Arrays.asList(
                        new Employee(1,"Tom",18,"male",10000),
                        new Employee(2,"Toots",18,"female",10000),
                        new Employee(3,"Jerry",18,"male",10000),
                        new Employee(4,"Nibbles",18,"male",10000),
                        new Employee(5,"Liz",18,"female",10000)
                );
        when(employeeRepositoryLegacy.pagination(1,5)).thenReturn(employees);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryLegacy);

        List<Employee> pagedEmployees = employeeService.pagination(1,5);

        assertSame(employees.size(), pagedEmployees.size());
    }
}
