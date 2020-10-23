package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getAllEmployees(){
        return employeeService.getAll();
    }

    @GetMapping("/{employeeID}")
    public Employee getEmployee(@PathVariable int employeeID)
    {
        return employeeService.findEmployee(employeeID);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<Employee> getPagedEmployees(int page, int pageSize){
        return employeeService.getEmployeeByPage(page, pageSize);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeesByGender(String gender){
        return employeeService.findEmployeesByGender(gender);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee newEmploy){
        return  employeeService.updateEmployee(employeeId, newEmploy);
    }

    @DeleteMapping("/{employeeID}")
    public void deleteEmployee(@PathVariable int employeeID){
        employeeService.deleteEmployee(employeeID);
    }
}
