package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
