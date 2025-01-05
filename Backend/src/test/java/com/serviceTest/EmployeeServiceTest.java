package com.serviceTest;

import com.dao.EmployeeDAO;
import com.model.Employee;
import com.service.EmployeeService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class  EmployeeServiceTest {
 
    @Mock
    private EmployeeDAO employeeDAO;
 
    @InjectMocks
    private EmployeeService employeeService;
 
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
        when(employeeDAO.save(testEmployee)).thenReturn(testEmployee);
 
        Employee result = employeeService.addEmployee(testEmployee);
 
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(employeeDAO, times(1)).save(testEmployee);
    }
 
    @Test
    void testAddEmployee_Invalid() {
        Employee result = employeeService.addEmployee(null);
 
        assertNull(result);
        verify(employeeDAO, never()).save(any());
    }
 
    @Test
    void testGetAllEmployees() {
        when(employeeDAO.findAll()).thenReturn(Arrays.asList(testEmployee));
 
        List<Employee> employees = employeeService.getAllEmployees();
 
        assertEquals(1, employees.size());
        assertEquals("John", employees.get(0).getName());
        verify(employeeDAO, times(1)).findAll();
    }
 
    @Test
    void testGetAllEmployees_Empty() {
        when(employeeDAO.findAll()).thenReturn(List.of());
 
        List<Employee> employees = employeeService.getAllEmployees();
 
        assertTrue(employees.isEmpty());
        verify(employeeDAO, times(1)).findAll();
    }
 
    @Test
    void testFindByEmployeeId_Found() {
        when(employeeDAO.existsById(1)).thenReturn(true);
        when(employeeDAO.findById(1)).thenReturn(Optional.of(testEmployee));
 
        Employee employee = employeeService.findByEmployeeId(1);
 
        assertNotNull(employee);
        assertEquals(1, employee.getEmployeeId());
        verify(employeeDAO, times(1)).findById(1);
    }
 
    @Test
    void testFindByEmployeeId_NotFound() {
        when(employeeDAO.existsById(1)).thenReturn(false);
 
        Employee employee = employeeService.findByEmployeeId(1);
 
        assertNull(employee);
        verify(employeeDAO, never()).findById(1);
    }
 
    @Test
    void testFindByName() {
        when(employeeDAO.findByName("John")).thenReturn(List.of(testEmployee));
 
        List<Employee> employees = employeeService.findByName("John");
 
        assertEquals(1, employees.size());
        assertEquals("John", employees.get(0).getName());
        verify(employeeDAO, times(1)).findByName("John");
    }
 
    @Test
    void testFindByName_NotFound() {
        when(employeeDAO.findByName("Unknown")).thenReturn(List.of());
 
        List<Employee> employees = employeeService.findByName("Unknown");
 
        assertTrue(employees.isEmpty());
        verify(employeeDAO, times(1)).findByName("Unknown");
    }
 
    @Test
    void testUpdateEmployee_Valid() {
        when(employeeDAO.existsById(1)).thenReturn(true);
        when(employeeDAO.save(testEmployee)).thenReturn(testEmployee);
 
        Employee updatedEmployee = employeeService.updateEmployee(testEmployee);
 
        assertNotNull(updatedEmployee);
        assertEquals("John", updatedEmployee.getName());
        verify(employeeDAO, times(1)).save(testEmployee);
    }
 
    @Test
    void testUpdateEmployee_NotFound() {
        when(employeeDAO.existsById(1)).thenReturn(false);
 
        Employee updatedEmployee = employeeService.updateEmployee(testEmployee);
 
        assertNull(updatedEmployee);
        verify(employeeDAO, never()).save(testEmployee);
    }
}
 
 
 
 
 