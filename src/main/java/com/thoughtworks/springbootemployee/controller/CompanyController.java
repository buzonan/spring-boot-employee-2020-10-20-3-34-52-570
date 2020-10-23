package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.models.company.Company;
import com.thoughtworks.springbootemployee.models.company.CompanyRequest;
import com.thoughtworks.springbootemployee.models.company.CompanyResponse;
import com.thoughtworks.springbootemployee.models.employee.Employee;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping()
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyService.getAll();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody CompanyRequest request){
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        Company savedCompany = companyService.createCompany(company);
        return companyMapper.toResponse(savedCompany);
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
    public Company updateCompany(@PathVariable int companyId, @RequestBody Company newCompany){
        return companyService.updateCompany(companyId, newCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable int companyId){
        companyService.deleteEmployees(companyId);
    }
}
