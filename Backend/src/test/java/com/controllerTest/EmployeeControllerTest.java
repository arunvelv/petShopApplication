package com.controllerTest;
 
import com.controller.EmployeeController;
import com.model.Employee;
import com.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;
 
class EmployeeControllerTest {
 
    @Mock
    private EmployeeService employeeService;
 
    @InjectMocks
    private EmployeeController employeeController;
 
    private Employee testEmployee;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        testEmployee = new Employee();
        testEmployee.setEmployeeId(1);
        testEmployee.setName("John");
        testEmployee.setPosition("Manager");
        testEmployee.setEmail("john.doe@example.com");
        testEmployee.setPhoneNumber("9876543210");
    }
 
    @Test
    void testAddEmployee_Valid() {
        when(employeeService.addEmployee(testEmployee)).thenReturn(testEmployee);
 
        ResponseEntity<?> response = employeeController.addEmployee(testEmployee);
 
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(testEmployee, response.getBody());
        verify(employeeService, times(1)).addEmployee(testEmployee);
    }
 
    @Test
    void testAddEmployee_Invalid() {
        when(employeeService.addEmployee(null)).thenReturn(null);
 
        ResponseEntity<?> response = employeeController.addEmployee(null);
 
        assertEquals(BAD_REQUEST, response.getStatusCode());
        verify(employeeService, times(1)).addEmployee(null);
    }
 
    @Test
    void testGetAllEmployees_Found() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(testEmployee));
 
        ResponseEntity<?> response = employeeController.getAllEmployees();
 
        assertEquals(OK, response.getStatusCode());
        List<Employee> employees = (List<Employee>) response.getBody();
        assertEquals(1, employees.size());
        assertEquals("John", employees.get(0).getName());
        verify(employeeService, times(1)).getAllEmployees();
    }
 
    @Test
    void testGetAllEmployees_NotFound() {
        when(employeeService.getAllEmployees()).thenReturn(List.of());
 
        ResponseEntity<?> response = employeeController.getAllEmployees();
 
        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).getAllEmployees();
    }
 
    @Test
    void testGetByEmployeeId_Found() {
        when(employeeService.findByEmployeeId(1)).thenReturn(testEmployee);
 
        ResponseEntity<?> response = employeeController.getByEmployeeId(1);
 
        assertEquals(OK, response.getStatusCode());
        assertEquals(testEmployee, response.getBody());
        verify(employeeService, times(1)).findByEmployeeId(1);
    }
 
    @Test
    void testGetByEmployeeId_NotFound() {
        when(employeeService.findByEmployeeId(1)).thenReturn(null);
 
        ResponseEntity<?> response = employeeController.getByEmployeeId(1);
 
        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).findByEmployeeId(1);
    }
 
    @Test
    void testGetByName_Found() {
        when(employeeService.findByName("John")).thenReturn(List.of(testEmployee));
 
        ResponseEntity<?> response = employeeController.getByName("John");
 
        assertEquals(OK, response.getStatusCode());
        List<Employee> employees = (List<Employee>) response.getBody();
        assertEquals(1, employees.size());
        verify(employeeService, times(1)).findByName("John");
    }
 
    @Test
    void testGetByName_NotFound() {
        when(employeeService.findByName("Unknown")).thenReturn(List.of());
 
        ResponseEntity<?> response = employeeController.getByName("Unknown");
 
        assertEquals(NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).findByName("Unknown");
    }
}
 
 