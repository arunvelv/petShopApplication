package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Customer;
import com.model.Pets;
import com.model.Transactions;

class TransactionsTest {

    private Transactions transaction;
    private Customer customer;
    private Pets pet;

    @BeforeEach
    void setUp() {
        // Initialize Customer
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");

        // Initialize Pets
        pet = new Pets();
        pet.setPetId(1);
        pet.setName("Buddy");

        // Initialize Transactions
        transaction = new Transactions();
        transaction.setTransactionId(1001);
        transaction.setTransactionDate(Date.valueOf("2023-12-25"));
        transaction.setAmount(250.50);
        transaction.setTransactionStatus(Transactions.TransactionStatus.SUCCESS);
        transaction.setCustomer(customer);
        transaction.setPet(pet);
    }

    @Test
    void testTransactionsDefaultConstructor() {
        Transactions defaultTransaction = new Transactions();
        assertNull(defaultTransaction.getTransactionDate());
        assertNull(defaultTransaction.getTransactionStatus());
        assertNull(defaultTransaction.getCustomer());
        assertNull(defaultTransaction.getPet());
    }

    @Test
    void testTransactionsGetters() {
        assertEquals(1001, transaction.getTransactionId());
        assertEquals(Date.valueOf("2023-12-25"), transaction.getTransactionDate());
        assertEquals(250.50, transaction.getAmount());
        assertEquals(Transactions.TransactionStatus.SUCCESS, transaction.getTransactionStatus());
        assertEquals(customer, transaction.getCustomer());
        assertEquals(pet, transaction.getPet());
    }

    @Test
    void testTransactionsSetters() {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(2);
        newCustomer.setFirstName("Jane");
        newCustomer.setLastName("Smith");
        newCustomer.setEmail("jane.smith@example.com");
        newCustomer.setPhoneNumber("987-654-3210");

        Pets newPet = new Pets();
        newPet.setPetId(2);
        newPet.setName("Charlie");

        transaction.setTransactionId(1002);
        transaction.setTransactionDate(Date.valueOf("2024-01-15"));
        transaction.setAmount(300.75);
        transaction.setTransactionStatus(Transactions.TransactionStatus.FAILED);
        transaction.setCustomer(newCustomer);
        transaction.setPet(newPet);

        assertEquals(1002, transaction.getTransactionId());
        assertEquals(Date.valueOf("2024-01-15"), transaction.getTransactionDate());
        assertEquals(300.75, transaction.getAmount());
        assertEquals(Transactions.TransactionStatus.FAILED, transaction.getTransactionStatus());
        assertEquals(newCustomer, transaction.getCustomer());
        assertEquals(newPet, transaction.getPet());
    }
}
