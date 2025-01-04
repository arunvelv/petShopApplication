package com.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Employee;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
	List<Employee> findByEmployeeId (int employeeId);
	List<Employee> findByName (String name);
	List<Employee> findByphoneNumber (String phoneNumber);
	List<Employee> findByEmail (String email);
	List<Employee> findByPosition (String position);
	List<Employee> findByAddressAddressId(int addressId);
	
}