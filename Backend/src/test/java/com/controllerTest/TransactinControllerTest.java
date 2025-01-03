package com.controllerTest;
 
import com.controller.TransactionsController;
import com.model.Transactions;
import com.service.TransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
 
class TransactionsControllerTest {
 
    @InjectMocks //Injects the TransactionsController with its mock dependencies.
    private TransactionsController transactionsController;
 
    @Mock
    private TransactionsService transactionService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddTransaction() {
        Transactions transaction = new Transactions(); // Create a Transactions instance
        //Calls the addTransaction method in the controller.
        ResponseEntity<String> response = transactionsController.addTransaction(transaction);
        //Ensures the HTTP status code is 201 Created
        assertEquals("Transaction added successfully.", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transactionService, times(1)).saveTransaction(transaction);
    }
 
    @Test
    void testGetTransactionsByIdFound() {
        Transactions transaction = new Transactions();
        //Mocks the service layer to return a Transactions object.
        when(transactionService.getTransactionById(anyInt())).thenReturn(transaction);
 
        ResponseEntity<Transactions> response = (ResponseEntity<Transactions>)transactionsController.getTransactionById(1);
 
        assertEquals(transaction, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getTransactionById(1);
    }
 
    @Test
    void testGetTransactionsByIdNotFound() {
    	//Simulates a scenario where the service returns null
        when(transactionService.getTransactionById(anyInt())).thenReturn(null); // Mock the service to return null
 
        ResponseEntity<?> response = transactionsController.getTransactionById(1); // Call the method under test
 
        assertNull(response.getBody()); // Assert that the body is null
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Assert the NOT_FOUND status
        verify(transactionService, times(1)).getTransactionById(1); // Verify the service was called once
    }
 
 
    @Test
    void testGetSuccessTransaction() {
        Transactions transaction1 = new Transactions();
        Transactions transaction2 = new Transactions();
        // Creates a list of successful transactions and mocks the service to return it.
        List<Transactions> transactions = Arrays.asList(transaction1, transaction2);
 
        when(transactionService.getSuccessTransactions()).thenReturn(transactions);
        //Calls getSuccessfulTransactions in the controller.
        ResponseEntity<List<Transactions>> response = transactionsController.getSuccessfulTransactions();
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getSuccessTransactions();
    }
 
    @Test
    void testGetFailedTransaction() {
        Transactions transaction1 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1);
 
        when(transactionService.getFailedTransactions()).thenReturn(transactions);
 
        ResponseEntity<List<Transactions>> response = (ResponseEntity<List<Transactions>>) transactionsController.getFailedTransactions();
        //Validates the list size for failed transactions.
        //Ensures the correct status code (200 OK).
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getFailedTransactions();
    }
 
    @Test
    void testGetTransactionsByCusId() {
        Transactions transaction1 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1);
        //Creates a transaction list and mocks the service for a customer ID.
        when(transactionService.getTransactionsByCustomerId(anyInt())).thenReturn(transactions);
       //Calls getTransactionsByCustomerId in the controller
        ResponseEntity<List<Transactions>> response1 = (ResponseEntity<List<Transactions>>) transactionsController.getTransactionsByCustomerId(1);
        //Validates the list contains the expected number of transactions.Confirms the status code is 200 OK
        assertEquals(1, response1.getBody().size());
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        
        verify(transactionService, times(1)).getTransactionsByCustomerId(1);
    }
 
    @Test
    void testGetAllTransactions() {
        Transactions transaction1 = new Transactions();
        Transactions transaction2 = new Transactions();
        //Sets up a list of transactions and mocks the service to return it.

        List<Transactions> transactions = Arrays.asList(transaction1, transaction2);
 
        when(transactionService.getAllTransactions()).thenReturn(transactions);
        //Calls the getAllTransactions controller method.
        ResponseEntity<List<Transactions>> response = transactionsController.getAllTransactions();
       // Verifies the list size matches expectations.Checks the status code (200 OK).
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
       // Confirms the getAllTransactions method is called once
        verify(transactionService, times(1)).getAllTransactions();
    }
}