package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByGender(String gender);
    List<Employee> findByCompanyId(int companyId);
}