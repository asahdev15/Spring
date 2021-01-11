package com.asahdev.service;

import java.util.List;
import java.util.Optional;

import com.asahdev.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asahdev.dao.EmployeeDAO;
import com.asahdev.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//	private EmployeeDAO employeeRepository;
	private EmployeeRepository employeeRepository;

	
//	@Autowired
//	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
//		employeeDAO = theEmployeeDAO;
//	}

//	@Autowired
//	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
//		employeeDAO = theEmployeeDAO;
//	}

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
