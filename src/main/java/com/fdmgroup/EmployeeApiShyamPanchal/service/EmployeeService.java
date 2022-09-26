package com.fdmgroup.EmployeeApiShyamPanchal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.EmployeeApiShyamPanchal.model.Employee;
import com.fdmgroup.EmployeeApiShyamPanchal.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;

	public EmployeeService(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> retrieveEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee retrieveEmployee(long empId) {
		Optional<Employee> empOpt = employeeRepo.findById(empId);
		
		if(!empOpt.isPresent()) {
			throw new EmployeeNotFoundException("Employee with id " + empId + " was not found.");
		}
		return empOpt.get();
	}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteEmployee(long empId) {
		employeeRepo.deleteById(empId);
	}
	
	

}
