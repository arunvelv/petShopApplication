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

    @InjectMocks
    private TransactionsController transactionsController;

    @Mock
    private TransactionsService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testAddTransaction() {
//        Transactions transaction = new Transactions(); // Create a Transactions instance
//
//        ResponseEntity<String> response = transactionsController.addTransaction(transaction);
//
//        assertEquals("Transactions added", response.getBody());
//        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//        verify(transactionService, times(1)).saveTransaction(transaction);
//    }

    @Test
    void testGetTransactionsByIdFound() {
        Transactions transaction = new Transactions();
        when(transactionService.getTransactionById(anyInt())).thenReturn(transaction);

        ResponseEntity<Transactions> response = (ResponseEntity<Transactions>)transactionsController.getTransactionById(1);

        assertEquals(transaction, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getTransactionById(1);
    }


    @Test
    void testGetSuccessTransaction() {
        Transactions transaction1 = new Transactions();
        Transactions transaction2 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getSuccessTransactions()).thenReturn(transactions);

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

        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getFailedTransactions();
    }

    @Test
    void testGetTransactionsByCusId() {
        Transactions transaction1 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1);

        when(transactionService.getTransactionsByCustomerId(anyInt())).thenReturn(transactions);

        ResponseEntity<List<Transactions>> response1 = (ResponseEntity<List<Transactions>>) transactionsController.getTransactionsByCustomerId(1);

        assertEquals(1, response1.getBody().size());
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        verify(transactionService, times(1)).getTransactionsByCustomerId(1);
    }

    @Test
    void testGetAllTransactions() {
        Transactions transaction1 = new Transactions();
        Transactions transaction2 = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        ResponseEntity<List<Transactions>> response = transactionsController.getAllTransactions();

        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transactionService, times(1)).getAllTransactions();
    }
}
