package com.jasperdemo.report.repository;

import com.jasperdemo.report.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
