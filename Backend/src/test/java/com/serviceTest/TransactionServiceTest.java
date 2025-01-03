package com.serviceTest;

import com.dao.CustomerDAO;
import com.dao.TransactionsDAO;
import com.model.Customer;
import com.model.Transactions;
import com.service.TransactionsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TransactionsServiceTest {

    @InjectMocks //Injects mock dependencies (transactionsDAO and customerDAO) into the transactionsService.
   
    private TransactionsService transactionsService;

    @Mock //Creates mock objects for TransactionsDAO and CustomerDAO.
    private TransactionsDAO transactionsDAO;

    @Mock
    private CustomerDAO customerDAO;
    private Transactions transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transactions();
        transaction.setTransactionId(1);
    }
    @Test
    public void testSaveTransaction_Success() {
        // Mock the customerDAO to return true when checking if a customer exists
        when(customerDAO.existsById(anyInt())).thenReturn(true);
 
        // Create a mock customer object
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(1); // Set the customer ID for the mock customer
        
        // Set the customer in the transaction object
        transaction.setCustomer(mockCustomer);
 
        // Mock the transactionsDAO to return the transaction when saving
        when(transactionsDAO.save(transaction)).thenReturn(transaction);
 
        // Call the method
        transactionsService.saveTransaction(transaction);
 
        // Verify that save method was called on the DAO
        verify(transactionsDAO, times(1)).save(transaction);
    }
 
 
    @Test
    public void testSaveTransaction_CustomerNotFound() {
        // Create a mock customer object with a customerId
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(1); // Set the customer ID for the mock customer
        
        // Set the customer in the transaction object
        transaction.setCustomer(mockCustomer);
 
        // Mock the customerDAO to return false when checking if a customer exists
        when(customerDAO.existsById(anyInt())).thenReturn(false);
 
        // Call the method and expect an exception
        try {
            transactionsService.saveTransaction(transaction);
        } catch (IllegalArgumentException e) {
            // Check that the exception is thrown with the correct message
            assert e.getMessage().equals("Customer not found with ID: " + transaction.getCustomer().getCustomerId());
        }
 
        // Verify that save was never called
        verify(transactionsDAO, times(0)).save(transaction);
    }

    @Test
    void testGetTransactionsByIdFound() {
        Transactions transaction = new Transactions();
     // Simulates finding a transaction in the database.

        when(transactionsDAO.findById(anyInt())).thenReturn(Optional.of(transaction));
        
     // Calls the service method to retrieve the transaction.
        
        Transactions result = transactionsService.getTransactionById(1);
        
     // Ensures the result is not null and matches the mocked transaction.
        
        assertNotNull(result);
        assertEquals(transaction, result);
     // Verifies that the findById method was called once with the correct ID.
        verify(transactionsDAO, times(1)).findById(1);
    }

    @Test
    public void testGetTransactionById_NotFound() {
        // Mock the transactionsDAO to return an empty Optional
        when(transactionsDAO.findById(1)).thenReturn(Optional.empty());
 
        // Call the method and expect an exception
        try {
            transactionsService.getTransactionById(1);
        } catch (IllegalArgumentException e) {
            // Check that the exception is thrown with the correct message
            assert e.getMessage().equals("Transaction not found with ID: 1");
        }
    }

    @Test
    void testGetSuccessTransaction() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
     // Simulates finding successful transactions in the database.
        when(transactionsDAO.findByTransactionStatus(Transactions.TransactionStatus.SUCCESS)).thenReturn(transactions);
     // Calls the service method to retrieve successful transactions.
        List<Transactions> result = transactionsService.getSuccessTransactions();
     // Ensures the result is not null and contains the expected transaction.
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
     // Verifies the correct DAO method was called once
        verify(transactionsDAO, times(1)).findByTransactionStatus(Transactions.TransactionStatus.SUCCESS);
    }

    @Test
    void testGetFailedTransaction() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionsDAO.findByTransactionStatus(Transactions.TransactionStatus.FAILED)).thenReturn(transactions);

        List<Transactions> result = transactionsService.getFailedTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
        verify(transactionsDAO, times(1)).findByTransactionStatus(Transactions.TransactionStatus.FAILED);
    }

    @Test
    void testGetTransactionsByCusId() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
     // Simulates retrieving transactions for a specific customer.
        when(transactionsDAO.findByCustomers_CustomerId(anyInt())).thenReturn(transactions);
     // Calls the service method to retrieve customer-specific transactions.
        List<Transactions> result = transactionsService.getTransactionsByCustomerId(1);
     // Verifies the result contains the expected transaction.
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
     // Ensures the correct DAO method was called.
        verify(transactionsDAO, times(1)).findByCustomers_CustomerId(1);
    }

    @Test
    void testGetAllTransactions() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
     // Simulates retrieving all transactions from the database.
        when(transactionsDAO.findAll()).thenReturn(transactions);
     // Calls the service method to retrieve all transactions.
        List<Transactions> result = transactionsService.getAllTransactions();
     // Ensures the result contains the expected transaction.
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
     // Verifies the DAO method was called exactly once.
        verify(transactionsDAO, times(1)).findAll();
    }
}
