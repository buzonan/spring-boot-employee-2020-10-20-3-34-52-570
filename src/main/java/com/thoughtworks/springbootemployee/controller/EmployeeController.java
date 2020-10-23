package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.models.employee.EmployeeRequest;
import com.thoughtworks.springbootemployee.models.employee.EmployeeResponse;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper){
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping()
    public List<EmployeeResponse> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAll();
        return employeeList.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{employeeID}")
    public EmployeeResponse getEmployee(@PathVariable int employeeID)
    {
        Employee employee = employeeService.findEmployee(employeeID);
        return employeeMapper.toResponse(employee);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<EmployeeResponse> getPagedEmployees(int page, int pageSize){
        List<Employee> employeeList = employeeService.getEmployeeByPage(page, pageSize);
        return employeeList.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> getEmployeesByGender(String gender){
        List<Employee> employees = employeeService.findEmployeesByGender(gender);
        return employees.stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody EmployeeRequest request){
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeService.createEmployee(employee));
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeRequest request){
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeService.updateEmployee(employeeId, employee));
    }

    @DeleteMapping("/{employeeID}")
    public void deleteEmployee(@PathVariable int employeeID){
        employeeService.deleteEmployee(employeeID);
    }
}
