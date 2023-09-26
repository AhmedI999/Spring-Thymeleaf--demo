package com.springboot.thymeleafsecuritydemo.service;

import com.springboot.thymeleafsecuritydemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int id);
	
	void save(Employee theEmployee);
	
	void deleteById(int id);
	
}
