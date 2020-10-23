package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
