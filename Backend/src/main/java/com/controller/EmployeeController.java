package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.InvalidInputException;
import com.model.Address;
import com.model.Customer;
import com.model.CustomerPayload;
import com.model.Employee;
import com.model.EmployeePayload;
import com.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @PostMapping("/add")
//    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
//        Employee savedEmployee = employeeService.addEmployee(employee);
//        if (savedEmployee != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee data");
//    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No employees found");
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getByEmployeeId(@PathVariable int employeeId) {
        Employee employee = employeeService.findByEmployeeId(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        List<Employee> employees = employeeService.findByName(name);
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found with name: " + name);
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<?> getByPosition(@PathVariable String position) {
        List<Employee> employees = employeeService.findByPosition(position);
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found with position: " + position);
    }

    @PutMapping("/update/{employeeId:[0-9]+}")
    public ResponseEntity<?> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        employee.setEmployeeId(employeeId);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found or update failed");
    }
    
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeePayload payload) {
    	Address address = payload.getAddress();
        Employee employees = payload.getEmployee();
          if (address == null || employees == null) {
            throw new InvalidInputException("VALIDATION_ERROR");
       }
       List<Employee> existingEmployees = employeeService.findByAddress(address.getAddressId());
        if (!existingEmployees.isEmpty()) {
           throw new InvalidInputException("ADD_FAILS");
       }
        Employee createdEmployee = employeeService.saveCustomer(employees, address);
           return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
}