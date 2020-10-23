package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.models.company.Company;
import com.thoughtworks.springbootemployee.models.company.CompanyRequest;
import com.thoughtworks.springbootemployee.models.company.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company){
        CompanyResponse response = new CompanyResponse();

        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());
        response.setEmployees(company.getEmployees());

        return response;
    }

    public Company toEntity(CompanyRequest request){
        Company company = new Company();

        company.setCompanyName(request.getCompanyName());

        return company;
    }
}
