package com.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        // Initialize the Address object before each test
        address = new Address();
    }

    @Test
    void testGetAddressId() {
        // Set an addressId
        address.setAddressId(1);

        // Assert that the addressId is correctly retrieved
        assertEquals(1, address.getAddressId(), "Address ID should be 1.");
    }

    @Test
    void testSetAddressId() {
        // Set the addressId and assert it's correctly set
        address.setAddressId(2);
        assertEquals(2, address.getAddressId(), "Address ID should be 2.");
    }

    @Test
    void testGetStreet() {
        // Set a street name
        address.setStreet("123 Main St");

        // Assert that the street name is correctly retrieved
        assertEquals("123 Main St", address.getStreet(), "Street should be '123 Main St'.");
    }

    @Test
    void testSetStreet() {
        // Set the street name and assert it's correctly set
        address.setStreet("456 Elm St");
        assertEquals("456 Elm St", address.getStreet(), "Street should be '456 Elm St'.");
    }

    @Test
    void testGetCity() {
        // Set the city name
        address.setCity("New York");

        // Assert that the city name is correctly retrieved
        assertEquals("New York", address.getCity(), "City should be 'New York'.");
    }

    @Test
    void testSetCity() {
        // Set the city name and assert it's correctly set
        address.setCity("Los Angeles");
        assertEquals("Los Angeles", address.getCity(), "City should be 'Los Angeles'.");
    }

    @Test
    void testGetState() {
        // Set the state name
        address.setState("NY");

        // Assert that the state name is correctly retrieved
        assertEquals("NY", address.getState(), "State should be 'NY'.");
    }

    @Test
    void testSetState() {
        // Set the state name and assert it's correctly set
        address.setState("California");
        assertEquals("California", address.getState(), "State should be 'California'.");
    }

    @Test
    void testGetZipCode() {
        // Set the zip code
        address.setZipCode("10001");

        // Assert that the zip code is correctly retrieved
        assertEquals("10001", address.getZipCode(), "Zip code should be '10001'.");
    }

    @Test
    void testSetZipCode() {
        // Set the zip code and assert it's correctly set
        address.setZipCode("90001");
        assertEquals("90001", address.getZipCode(), "Zip code should be '90001'.");
    }

    @Test
    void testAddressConstructor() {
        // Create an Address object using the constructor
        Address newAddress = new Address(1, "789 Oak St", "Chicago", "IL", "60601");

        // Assert that the values are correctly set in the constructor
        assertEquals(1, newAddress.getAddressId(), "Address ID should be 1.");
        assertEquals("789 Oak St", newAddress.getStreet(), "Street should be '789 Oak St'.");
        assertEquals("Chicago", newAddress.getCity(), "City should be 'Chicago'.");
        assertEquals("IL", newAddress.getState(), "State should be 'IL'.");
        assertEquals("60601", newAddress.getZipCode(), "Zip code should be '60601'.");
    }
}

