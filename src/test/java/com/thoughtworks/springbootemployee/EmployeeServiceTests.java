package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.repositories.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTests {
    @Test
    void should_return_all_employees_when_get_all_employees_given() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expectedEmployeeList = asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        List<Employee> actualEmployees = employeeService.getAll();

        assertEquals(expectedEmployeeList.size(), actualEmployees.size());
    }

    @Test
    void should_return_employee_when_add_employee_given_new_employee() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee expectedEmployee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepository.addEmployee(expectedEmployee)).thenReturn(expectedEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        Employee actualEmployee = employeeService.createEmployee(expectedEmployee);

        assertEquals(expectedEmployee.getEmployeeID(), actualEmployee.getEmployeeID());
    }

    @Test
    void should_return_employee_when_update_employee_given_new_employee() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        Employee updatedEmployee = new Employee(1,"Tom",18,"male",12000);

        when(employeeRepository.addEmployee(employee)).thenReturn(updatedEmployee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        employeeService.updateEmployee(employee.getEmployeeID(), updatedEmployee);

        Mockito.verify(employeeRepository).updateEmployee(employee.getEmployeeID(), updatedEmployee);
    }

    @Test
    void should_return_when_delete_employee_given_employee_id() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepository.addEmployee(employee)).thenReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        employeeService.deleteEmployee(employee.getEmployeeID());

        Mockito.verify(employeeRepository).deleteEmployee(employee.getEmployeeID());
    }

    @Test
    void should_return_employee_when_find_employee_given_employee_id() {
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        Employee employee = new Employee(1,"Tom",18,"male",10000);
        when(employeeRepository.findEmployee(employee.getEmployeeID())).thenReturn(employee);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        Employee foundEmployee = employeeService.findEmployee(employee.getEmployeeID());

        assertSame(employee, foundEmployee);
    }
}
