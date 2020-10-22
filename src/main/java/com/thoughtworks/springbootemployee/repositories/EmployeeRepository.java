package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByGender(String gender);
}
