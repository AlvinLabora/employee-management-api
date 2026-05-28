package com.example.employeeapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeRepository.findById(id).orElse(null);
	
	}
	
	@PutMapping("/{id}")
	public Employee udpateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
		Employee employee = employeeRepository.findById(id).orElse(null);

		if (employee == null) {
			return null;
		}

		employee.setName(newEmployee.getName());
		employee.setEmail(newEmployee.getEmail());
		employee.setDepartment(newEmployee.getDepartment());
		employee.setSalary(newEmployee.getSalary());
		employee.setAge(newEmployee.getAge());

		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return "Employee deleted successfully";
		
	}
	
	@GetMapping("/department/{department}")
	public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
		return employeeRepository.findByDepartment(department);
	}
	
}
