package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Employee;
import com.model.Pets;

class EmployeeTest {

    private Employee employee;
    private Address address;
    private Pets pet1;
    private Pets pet2;

    @BeforeEach
    void setUp() {
        // Initialize Address
        address = new Address();
        address.setAddressId(1);
        address.setStreet("123 Main St");
        address.setCity("Metropolis");
        address.setState("NY");
        address.setZipCode("10101");

        // Initialize Pets
        pet1 = new Pets();
        pet1.setPetId(1);
        pet1.setName("Buddy");

        pet2 = new Pets();
        pet2.setPetId(2);
        pet2.setName("Milo");

        List<Pets> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);

        // Initialize Employee
        employee = new Employee();
        employee.setEmployeeId(101);
        employee.setName("John Doe");
        employee.setPosition("Veterinarian");
        employee.setHireDate(Date.valueOf("2020-01-01"));
        employee.setPhoneNumber("123-456-7890");
        employee.setEmail("john.doe@example.com");
        employee.setAddress(address);
        employee.setPets(pets);
    }

    @Test
    void testEmployeeDefaultConstructor() {
        Employee defaultEmployee = new Employee();
        assertNull(defaultEmployee.getName());
        assertNull(defaultEmployee.getPosition());
        assertNull(defaultEmployee.getHireDate());
        assertNull(defaultEmployee.getPhoneNumber());
        assertNull(defaultEmployee.getEmail());
        assertNull(defaultEmployee.getAddress());
        assertNull(defaultEmployee.getPets());
    }

    @Test
    void testEmployeeGetters() {
        assertEquals(101, employee.getEmployeeId());
        assertEquals("John Doe", employee.getName());
        assertEquals("Veterinarian", employee.getPosition());
        assertEquals(Date.valueOf("2020-01-01"), employee.getHireDate());
        assertEquals("123-456-7890", employee.getPhoneNumber());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals(address, employee.getAddress());
        assertEquals(2, employee.getPets().size());
        assertEquals(pet1, employee.getPets().get(0));
        assertEquals(pet2, employee.getPets().get(1));
    }

    @Test
    void testEmployeeSetters() {
        Address newAddress = new Address();
        newAddress.setAddressId(2);
        newAddress.setStreet("456 Elm St");
        newAddress.setCity("Gotham");
        newAddress.setState("NJ");
        newAddress.setZipCode("07001");

        employee.setName("Jane Smith");
        employee.setPosition("Receptionist");
        employee.setHireDate(Date.valueOf("2022-06-15"));
        employee.setPhoneNumber("987-654-3210");
        employee.setEmail("jane.smith@example.com");
        employee.setAddress(newAddress);

        List<Pets> newPets = new ArrayList<>();
        Pets pet3 = new Pets();
        pet3.setPetId(3);
        pet3.setName("Charlie");
        newPets.add(pet3);

        employee.setPets(newPets);

        assertEquals("Jane Smith", employee.getName());
        assertEquals("Receptionist", employee.getPosition());
        assertEquals(Date.valueOf("2022-06-15"), employee.getHireDate());
        assertEquals("987-654-3210", employee.getPhoneNumber());
        assertEquals("jane.smith@example.com", employee.getEmail());
        assertEquals(newAddress, employee.getAddress());
        assertEquals(1, employee.getPets().size());
        assertEquals(pet3, employee.getPets().get(0));
    }
}

