package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDAO;
import com.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public Employee addEmployee(Employee employee) {
        if (employee != null) {
            return employeeDAO.save(employee);
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeDAO.findAll();
        if (employees != null && !employees.isEmpty()) {
            return employees;
        }
        return List.of(); 
    }

    public Employee updateEmployee(Employee employee) {
        if (employee != null && employeeDAO.existsById(employee.getEmployeeId())) {
            return employeeDAO.save(employee);
        }
        return null;
    }

    public Employee findByEmployeeId(int employeeId) {
        if (employeeDAO.existsById(employeeId)) {
            return employeeDAO.findById(employeeId).orElse(null);
        }
        return null;
    }

    public List<Employee> findByName(String name) {
        if (name != null && !name.isEmpty()) {
            List<Employee> employees = employeeDAO.findByName(name);
            if (employees != null && !employees.isEmpty()) {
                return employees;
            }
        }
        return List.of();
    }

    public List<Employee> findByPosition(String position) {
        if (position != null && !position.isEmpty()) {
            List<Employee> employees = employeeDAO.findByPosition(position);
            if (employees != null && !employees.isEmpty()) {
                return employees;
            }
        }
        return List.of();
    }
}