package com.stormtech.demo.service;

import java.util.List;

import com.stormtech.demo.models.Employee;

public interface EmployeeService {
	List<Employee>getAllEmployees();
	void saveEmployee(Employee employee);
}
