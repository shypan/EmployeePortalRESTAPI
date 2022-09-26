package com.fdmgroup.EmployeeApiShyamPanchal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.EmployeeApiShyamPanchal.model.Employee;
import com.fdmgroup.EmployeeApiShyamPanchal.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.headers.Header;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@Operation(summary = "Gets all employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
		@Content(mediaType = MediaType.APPLICATION_XML_VALUE) } , headers = {
				@Header(name = "location", description = "url to location of the resource")},
		description = "Gets data for all employees."),
		@ApiResponse(responseCode = "404", description = "Employee not found.")
	})
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		
		List<Employee> employees = employeeService.retrieveEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@Operation(summary = "Gets an employee based on id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
		@Content(mediaType = MediaType.APPLICATION_XML_VALUE) } , headers = {
				@Header(name = "location", description = "url to location of the resource")},
		description = "Gets data for an employee."),
		@ApiResponse(responseCode = "404", description = "Employee not found.")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") long empId) {
		
		Employee employee = employeeService.retrieveEmployee(empId);
		return ResponseEntity.ok(employee);
	}
	
	@Operation(summary = "Creates an employee based on id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
		@Content(mediaType = MediaType.APPLICATION_XML_VALUE) } , headers = {
				@Header(name = "location", description = "url to location of the resource")},
		description = "Creates data for an employee."),
		@ApiResponse(responseCode = "404", description = "Employee not found.")
	})
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		
		Employee createdEmployee = employeeService.createEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId()).toUri();
		
		return ResponseEntity.created(location).body(createdEmployee);
	}
	
	@Operation(summary = "Updates an employee based on id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
		@Content(mediaType = MediaType.APPLICATION_XML_VALUE) } , headers = {
				@Header(name = "location", description = "url to location of the resource")},
		description = "Updates data for an employee."),
		@ApiResponse(responseCode = "404", description = "Employee not found.")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long empId, @RequestBody Employee employee) {
		
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@Operation(summary = "Deletes an employee based on id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
		@Content(mediaType = MediaType.APPLICATION_XML_VALUE) } , headers = {
				@Header(name = "location", description = "url to location of the resource")},
		description = "Deletes data for an employee."),
		@ApiResponse(responseCode = "404", description = "Employee not found.")
	})
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") long empId) {
		employeeService.deleteEmployee(empId);
	}
	

}
