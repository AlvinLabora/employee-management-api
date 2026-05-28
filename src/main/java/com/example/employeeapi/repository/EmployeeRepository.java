package com.example.employeeapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByDepartment(String department);
}
