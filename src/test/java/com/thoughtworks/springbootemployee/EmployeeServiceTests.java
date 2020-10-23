package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceTests {

    @Test
    void should_return_all_employees_when_get_all_employees_given() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expectedEmployeeList = asList(new Employee(), new Employee());
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);
        List<Employee> actualEmployees = employeeService.getAll();

        //then
        assertEquals(expectedEmployeeList.size(), actualEmployees.size());
    }

    @Test
    void should_return_employee_when_add_employee_given_new_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee expectedEmployee = new Employee(1,"Tom",18,"male",10000);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);
        Employee actualEmployee = employeeService.createEmployee(expectedEmployee);

        //then
        assertEquals(expectedEmployee.getEmployeeId(), actualEmployee.getEmployeeId());
    }

    @Test
    void should_return_employee_when_update_employee_given_new_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        Employee updatedEmployee = new Employee(1,"Tom",18,"male",12000);

        //when
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(java.util.Optional.of(updatedEmployee));
        when(employeeRepository.save(employee)).thenReturn(updatedEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee actual = employeeService.updateEmployee(employee.getEmployeeId(), updatedEmployee);

        //then
        assertNotEquals(employee.getSalary(), actual.getSalary());
    }

    @Test
    void should_return_when_delete_employee_given_employee_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.deleteEmployee(employee.getEmployeeId());

        //then
        Mockito.verify(employeeRepository).deleteById(employee.getEmployeeId());
    }

    @Test
    void should_return_employee_when_find_employee_given_employee_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(java.util.Optional.of(employee));
        Employee foundEmployee = employeeService.findEmployee(employee.getEmployeeId());

        //then
        assertSame(employee, foundEmployee);
    }

    @Test
    void should_return_employee_list_when_find_employee_given_employee_gender() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        List<Employee> employees =
                Arrays.asList(
                        new Employee(1,"Tom",18,"male",10000),
                        new Employee(2,"Toots",18,"male",10000)
                );

        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        when(employeeRepository.findByGender("male")).thenReturn(employees);
        List<Employee> foundEmployees = employeeService.findEmployeesByGender("male");

        //then
        assertSame(employees.size(), foundEmployees.size());
    }

    @Test
    void should_return_employee_list_when_find_employee_given_page_and_page_size() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        List<Employee> employees =
                Arrays.asList(
                        new Employee(1,"Tom",18,"male",10000),
                        new Employee(2,"Toots",18,"female",10000),
                        new Employee(3,"Jerry",18,"male",10000),
                        new Employee(4,"Nibbles",18,"male",10000),
                        new Employee(5,"Liz",18,"female",10000)
                );
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        //when
        Pageable pageable = PageRequest.of(0,5);
        Page<Employee> employeePage = new PageImpl<>(employees);
        when(employeeRepository.findAll(pageable)).thenReturn(employeePage);
        List<Employee> pagedEmployees = employeeService.getEmployeeByPage(0,5);

        //then
        assertSame(employees.size(), pagedEmployees.size());
    }
}
