package com.asahdev.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asahdev.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
}