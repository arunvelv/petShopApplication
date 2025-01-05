package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Pets;
import com.model.Suppliers;

public class SupplierTest {

    private Suppliers supplier;
    private Address address;
    private List<Pets> petsList;

    @BeforeEach
    public void setUp() {
        address = new Address();
        address.setAddressId(1);
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setZipCode("12345");

        petsList = new ArrayList<>();
        Pets pet = new Pets();
        pet.setPetId(1);
        pet.setName("Buddy");
        pet.setBreed("Dog");
        petsList.add(pet);

        supplier = new Suppliers(1, "Supplier Name", "John Doe", "1234567890", "supplier@example.com", address);
        supplier.setPets(petsList);
    }

    @Test
    public void testGettersAndSetters() {
        // Test supplier ID
        assertEquals(1, supplier.getSuppliersId());
        supplier.setSuppliersId(2);
        assertEquals(2, supplier.getSuppliersId());

        // Test name
        assertEquals("Supplier Name", supplier.getName());
        supplier.setName("New Supplier Name");
        assertEquals("New Supplier Name", supplier.getName());

        // Test contact person
        assertEquals("John Doe", supplier.getContactPerson());
        supplier.setContactPerson("Jane Smith");
        assertEquals("Jane Smith", supplier.getContactPerson());

        // Test phone number
        assertEquals("1234567890", supplier.getPhoneNumber());
        supplier.setPhoneNumber("0987654321");
        assertEquals("0987654321", supplier.getPhoneNumber());

        // Test email
        assertEquals("supplier@example.com", supplier.getEmail());
        supplier.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", supplier.getEmail());

        // Test address
        assertEquals(address, supplier.getAddress());
        Address newAddress = new Address();
        newAddress.setAddressId(2);
        newAddress.setStreet("456 Another St");
        newAddress.setCity("New City");
        newAddress.setZipCode("67890");
        supplier.setAddress(newAddress);
        assertEquals(newAddress, supplier.getAddress());

        // Test pets
        assertEquals(petsList, supplier.getPets());
        List<Pets> newPetsList = new ArrayList<>();
        Pets newPet = new Pets();
        newPet.setPetId(2);
        newPet.setName("Kitty");
        newPet.setBreed("Cat");
        newPetsList.add(newPet);
        supplier.setPets(newPetsList);
        assertEquals(newPetsList, supplier.getPets());
    }
}
