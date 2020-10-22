package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.models.Company;
import com.thoughtworks.springbootemployee.models.Employee;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable int companyId){
        return companyService.findCompany(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyID(@PathVariable int companyId){
        return companyService.findEmployeeByCompanyId(companyId);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<Company> getPagedCompanies(int page, int pageSize){
        return companyService.getCompanyByPage(page, pageSize);
    }

    @PutMapping("/{companyId}")
    public Company updateCompany(@PathVariable int companyId, @RequestBody Company newComp){
        return companyService.updateCompany(companyId, newComp);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable int companyId){
        companyService.deleteEmployees(companyId);
    }
}
