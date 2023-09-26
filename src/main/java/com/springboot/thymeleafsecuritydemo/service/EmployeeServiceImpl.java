package com.springboot.thymeleafsecuritydemo.service;

import java.util.List;
import java.util.Optional;

import com.springboot.thymeleafsecuritydemo.dao.EmployeeRepository;
import com.springboot.thymeleafsecuritydemo.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		Employee theEmployee;
		if (result.isPresent()) {
			theEmployee = result.get();
			return theEmployee;
		}
		else {
			throw new RuntimeException("Did not find employee id - " + id);
		}
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

}






