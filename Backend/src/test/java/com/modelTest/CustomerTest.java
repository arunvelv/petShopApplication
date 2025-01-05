package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Customer;
import com.model.Transactions;

class CustomerTest {

    private Customer customer;
    private Address address;
    private Transactions transaction1;
    private Transactions transaction2;

    @BeforeEach
    void setUp() {
        // Initialize Address
        address = new Address();
        address.setAddressId(1);
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZipCode("62704");

        // Initialize Transactions
        transaction1 = new Transactions();
        transaction1.setTransactionId(101);
        transaction1.setAmount(200.0);

        transaction2 = new Transactions();
        transaction2.setTransactionId(102);
        transaction2.setAmount(150.0);

        List<Transactions> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        // Initialize Customer
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");
        customer.setAddress(address);
        customer.setTransactions(transactions);
    }

    @Test
    void testCustomerDefaultConstructor() {
        Customer emptyCustomer = new Customer();
        assertNull(emptyCustomer.getFirstName());
        assertNull(emptyCustomer.getLastName());
        assertNull(emptyCustomer.getEmail());
        assertNull(emptyCustomer.getPhoneNumber());
        assertNull(emptyCustomer.getAddress());
        assertNull(emptyCustomer.getTransactions());
    }

    @Test
    void testCustomerGetters() {
        assertEquals(1, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("123-456-7890", customer.getPhoneNumber());
        assertEquals(address, customer.getAddress());
        assertEquals(2, customer.getTransactions().size());
        assertEquals(transaction1, customer.getTransactions().get(0));
        assertEquals(transaction2, customer.getTransactions().get(1));
    }

    @Test
    void testCustomerSetters() {
        Address newAddress = new Address();
        newAddress.setAddressId(2);
        newAddress.setStreet("456 Elm St");
        newAddress.setCity("Chicago");
        newAddress.setState("IL");
        newAddress.setZipCode("60601");

        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setEmail("jane.smith@example.com");
        customer.setPhoneNumber("987-654-3210");
        customer.setAddress(newAddress);

        List<Transactions> newTransactions = new ArrayList<>();
        Transactions transaction3 = new Transactions();
        transaction3.setTransactionId(103);
        transaction3.setAmount(300.0);
        newTransactions.add(transaction3);

        customer.setTransactions(newTransactions);

        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jane.smith@example.com", customer.getEmail());
        assertEquals("987-654-3210", customer.getPhoneNumber());
        assertEquals(newAddress, customer.getAddress());
        assertEquals(1, customer.getTransactions().size());
        assertEquals(transaction3, customer.getTransactions().get(0));
    }
}