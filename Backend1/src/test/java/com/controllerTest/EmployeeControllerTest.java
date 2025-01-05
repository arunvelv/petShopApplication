package com.controllerTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controller.EmployeeController;
import com.exception.InvalidInputException;
import com.model.Address;
import com.model.Employee;
import com.model.EmployeePayload;
import com.service.EmployeeService;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;
    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        address = new Address();
        address.setAddressId(1);
        address.setCity("New York");

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("John Doe");
        employee.setPosition("Manager");
        employee.setPhoneNumber("1234567890");
        employee.setEmail("john.doe@example.com");
        employee.setAddress(address);
    }

    @Test
    void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        ResponseEntity<?> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Employee> employees = (List<Employee>) response.getBody();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void testGetAllEmployeesNoContent() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No employees found", response.getBody());
    }

    @Test
    void testGetByEmployeeIdFound() {
        when(employeeService.findByEmployeeId(1)).thenReturn(employee);

        ResponseEntity<?> response = employeeController.getByEmployeeId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Employee responseEmployee = (Employee) response.getBody();
        assertNotNull(responseEmployee);
        assertEquals("John Doe", responseEmployee.getName());
    }

    @Test
    void testGetByEmployeeIdNotFound() {
        when(employeeService.findByEmployeeId(2)).thenReturn(null);

        ResponseEntity<?> response = employeeController.getByEmployeeId(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employee not found", response.getBody());
    }

    @Test
    void testGetByNameFound() {
        when(employeeService.findByName("John Doe")).thenReturn(Arrays.asList(employee));

        ResponseEntity<?> response = employeeController.getByName("John Doe");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Employee> employees = (List<Employee>) response.getBody();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void testGetByNameNotFound() {
        when(employeeService.findByName("Jane Doe")).thenReturn(Arrays.asList());

        ResponseEntity<?> response = employeeController.getByName("Jane Doe");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No employees found with name: Jane Doe", response.getBody());
    }

    @Test
    void testGetByPositionFound() {
        when(employeeService.findByPosition("Manager")).thenReturn(Arrays.asList(employee));

        ResponseEntity<?> response = employeeController.getByPosition("Manager");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Employee> employees = (List<Employee>) response.getBody();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void testGetByPositionNotFound() {
        when(employeeService.findByPosition("Engineer")).thenReturn(Arrays.asList());

        ResponseEntity<?> response = employeeController.getByPosition("Engineer");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No employees found with position: Engineer", response.getBody());
    }

    @Test
    void testUpdateEmployeeSuccess() {
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<?> response = employeeController.updateEmployee(1, employee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Employee responseEmployee = (Employee) response.getBody();
        assertNotNull(responseEmployee);
        assertEquals("John Doe", responseEmployee.getName());
    }

    @Test
    void testUpdateEmployeeNotFound() {
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(null);

        ResponseEntity<?> response = employeeController.updateEmployee(2, employee);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employee not found or update failed", response.getBody());
    }

    @Test
    void testAddEmployeeSuccess() {
        EmployeePayload payload = new EmployeePayload();
        payload.setEmployee(employee);
        payload.setAddress(address);

        when(employeeService.findByAddress(1)).thenReturn(Arrays.asList());
        when(employeeService.saveCustomer(any(Employee.class), any(Address.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.addEmployee(payload);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Employee responseEmployee = response.getBody();
        assertNotNull(responseEmployee);
        assertEquals("John Doe", responseEmployee.getName());
    }

    @Test
    void testAddEmployeeInvalidInput() {
        EmployeePayload payload = new EmployeePayload();
        payload.setEmployee(null);
        payload.setAddress(null);

        assertThrows(InvalidInputException.class, () -> {
            employeeController.addEmployee(payload);
        });
    }

    @Test
    void testAddEmployeeConflict() {
        EmployeePayload payload = new EmployeePayload();
        payload.setEmployee(employee);
        payload.setAddress(address);

        when(employeeService.findByAddress(1)).thenReturn(Arrays.asList(employee));

        assertThrows(InvalidInputException.class, () -> {
            employeeController.addEmployee(payload);
        });
    }
}
