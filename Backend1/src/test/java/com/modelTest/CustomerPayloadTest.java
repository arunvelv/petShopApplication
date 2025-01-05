package com.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Customer;
import com.model.CustomerPayload;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPayloadTest {

    private CustomerPayload customerPayload;
    private Customer customer;
    private Address address;

    @BeforeEach
    void setUp() {
        // Create instances of Customer and Address for the test
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");

        address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setStreet("123 Main St");

        // Create instance of CustomerPayload
        customerPayload = new CustomerPayload();
    }

    @Test
    void testGetCustomer() {
        // Set customer to the payload
        customerPayload.setCustomer(customer);

        // Assert that the customer is correctly retrieved
        assertNotNull(customerPayload.getCustomer(), "Customer should not be null.");
        assertEquals("John", customerPayload.getCustomer().getFirstName(), "Customer first name should be 'John'.");
        assertEquals("Doe", customerPayload.getCustomer().getLastName(), "Customer last name should be 'Doe'.");
    }

    @Test
    void testSetCustomer() {
        // Set the customer in the payload
        customerPayload.setCustomer(customer);

        // Assert that the customer was correctly set
        assertEquals(customer, customerPayload.getCustomer(), "Customer in payload should match the set customer.");
    }

    @Test
    void testGetAddress() {
        // Set address to the payload
        customerPayload.setAddress(address);

        // Assert that the address is correctly retrieved
        assertNotNull(customerPayload.getAddress(), "Address should not be null.");
        assertEquals("City1", customerPayload.getAddress().getCity(), "Address city should be 'City1'.");
        assertEquals("State1", customerPayload.getAddress().getState(), "Address state should be 'State1'.");
    }

    @Test
    void testSetAddress() {
        // Set the address in the payload
        customerPayload.setAddress(address);

        // Assert that the address was correctly set
        assertEquals(address, customerPayload.getAddress(), "Address in payload should match the set address.");
    }

    @Test
    void testCustomerPayloadConstructor() {
        // Create a new CustomerPayload instance with customer and address
        CustomerPayload payload = new CustomerPayload();
        payload.setCustomer(customer);
        payload.setAddress(address);

        // Assert that customer and address are correctly set in the payload
        assertEquals(customer, payload.getCustomer(), "Customer in payload should match the set customer.");
        assertEquals(address, payload.getAddress(), "Address in payload should match the set address.");
    }
}

