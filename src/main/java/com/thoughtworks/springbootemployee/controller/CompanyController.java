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
        Company company = companyMapper.toEntity(request);
        companyService.createCompany(company);
        return companyMapper.toResponse(company);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompany(@PathVariable int companyId){
        Company company = companyService.findCompany(companyId);
        return companyMapper.toResponse(company);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeesByCompanyID(@PathVariable int companyId){
        return companyService.findEmployeeByCompanyId(companyId);

    }

    @GetMapping(params = {"page","pageSize"})
    public List<CompanyResponse> getPagedCompanies(int page, int pageSize){
        List<Company> companies = companyService.getCompanyByPage(page, pageSize);
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PatchMapping("/{companyId}")
    public CompanyResponse updateCompany(@PathVariable int companyId, @RequestBody CompanyRequest request){
        Company company = companyMapper.toEntity(request);
        Company updatedCompany = companyService.updateCompany(companyId, company);
        return companyMapper.toResponse(updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable int companyId){
        companyService.deleteEmployees(companyId);
    }
}
