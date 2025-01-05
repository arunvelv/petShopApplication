package com.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Employee;
import com.model.EmployeePayload;
import com.model.Pets;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeePayloadTest {

    private EmployeePayload employeePayload;
    private Employee employee;
    private Address address;
    private List<Pets> pets;

    @BeforeEach
    void setUp() {
        // Initialize Address
        address = new Address();
        address.setCity("New York");
        address.setState("NY");
        address.setStreet("456 Elm St");

        // Initialize Pets
        pets = new ArrayList<>();
        Pets pet1 = new Pets(); 
        pet1.setName("Fluffy");
        pet1.setBreed("Dog");
        pets.add(pet1);

        // Create Employee instance with necessary details
        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("John Doe");
        employee.setPosition("Manager");
        employee.setHireDate(Date.valueOf("2020-01-01"));
        employee.setPhoneNumber("123-456-7890");
        employee.setEmail("john.doe@example.com");
        employee.setAddress(address);
        employee.setPets(pets);

        // Create EmployeePayload instance
        employeePayload = new EmployeePayload();
    }

    @Test
    void testGetEmployee() {
        // Set the employee in the payload
        employeePayload.setEmployee(employee);

        // Assert that the employee is correctly retrieved
        assertNotNull(employeePayload.getEmployee(), "Employee should not be null.");
        assertEquals("John Doe", employeePayload.getEmployee().getName(), "Employee name should be 'John Doe'.");
        assertEquals("Manager", employeePayload.getEmployee().getPosition(), "Employee position should be 'Manager'.");
        assertEquals("2020-01-01", employeePayload.getEmployee().getHireDate().toString(), "Hire date should be '2020-01-01'.");
        assertEquals("123-456-7890", employeePayload.getEmployee().getPhoneNumber(), "Phone number should be '123-456-7890'.");
        assertEquals("john.doe@example.com", employeePayload.getEmployee().getEmail(), "Email should be 'john.doe@example.com'.");
    }

    @Test
    void testSetEmployee() {
        // Set the employee in the payload
        employeePayload.setEmployee(employee);

        // Assert that the employee was correctly set
        assertEquals(employee, employeePayload.getEmployee(), "Employee in payload should match the set employee.");
    }

    @Test
    void testGetAddress() {
        // Set the address to the payload
        employeePayload.setAddress(address);

        // Assert that the address is correctly retrieved
        assertNotNull(employeePayload.getAddress(), "Address should not be null.");
        assertEquals("New York", employeePayload.getAddress().getCity(), "Address city should be 'New York'.");
        assertEquals("NY", employeePayload.getAddress().getState(), "Address state should be 'NY'.");
        assertEquals("456 Elm St", employeePayload.getAddress().getStreet(), "Address street should be '456 Elm St'.");
    }

    @Test
    void testSetAddress() {
        // Set the address in the payload
        employeePayload.setAddress(address);

        // Assert that the address was correctly set
        assertEquals(address, employeePayload.getAddress(), "Address in payload should match the set address.");
    }

    @Test
    void testEmployeePayloadConstructor() {
        // Create a new EmployeePayload instance with employee and address
        EmployeePayload payload = new EmployeePayload();
        payload.setEmployee(employee);
        payload.setAddress(address);

        // Assert that employee and address are correctly set in the payload
        assertEquals(employee, payload.getEmployee(), "Employee in payload should match the set employee.");
        assertEquals(address, payload.getAddress(), "Address in payload should match the set address.");
    }

    @Test
    void testGetPets() {
        // Set the pets list in the employee
        employeePayload.setEmployee(employee);

        // Assert that the pets list is correctly retrieved
        assertNotNull(employeePayload.getEmployee().getPets(), "Pets list should not be null.");
        assertEquals(1, employeePayload.getEmployee().getPets().size(), "Employee should have one pet.");
        assertEquals("Fluffy", employeePayload.getEmployee().getPets().get(0).getName(), "Pet name should be 'Fluffy'.");
    }

    @Test
    void testSetPets() {
        // Set a new pets list
        Pets pet2 = new Pets();
        pet2.setName("Buddy");
        pet2.setBreed("Cat");
        List<Pets> newPetsList = new ArrayList<>();
        newPetsList.add(pet2);

        employee.setPets(newPetsList);
        employeePayload.setEmployee(employee);

        // Assert that the pets list was updated
        assertEquals("Buddy", employeePayload.getEmployee().getPets().get(0).getName(), "Pet name should be 'Buddy'.");
    }
}