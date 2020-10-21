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

    //GET       /companies    #obtain company list
    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    //GET       /companies/1  #obtain a certain specific company
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @GetMapping("/{companyID}")
    public Company getCompany(@PathVariable int companyID){
        return companyService.findCompany(companyID);
    }

    @GetMapping("/{companyID}/employees")
    public List<Employee> getEmployeesByCompanyID(@PathVariable int companyID){
        return companyService.findEmployeeByCompanyID(companyID);
    }

    @GetMapping(params = {"page","pageSize"})
    public List<Company> getPagedCompanies(int page, int pageSize){
        return companyService.pagination(page, pageSize);
    }

    @PutMapping("/{companyID}")
    public void updateCompany(@PathVariable int companyID, @RequestBody Company newComp){
        companyService.updateCompany(companyID, newComp);
    }

    @DeleteMapping("/{companyID}")
    public void deleteEmployee(@PathVariable int companyID){
        companyService.deleteEmployees(companyID);
    }
}
