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

    @InjectMocks
    private TransactionsService transactionsService;

    @Mock
    private TransactionsDAO transactionsDAO;

    @Mock
    private CustomerDAO customerDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testAddTransaction() {
//        Transactions transaction = new Transactions();  
//        transactionsService.saveTransaction(transaction);
//        verify(transactionsDAO, times(1)).save(transaction);
//    }

    @Test
    void testGetTransactionsByIdFound() {
        Transactions transaction = new Transactions();
        when(transactionsDAO.findById(anyInt())).thenReturn(Optional.of(transaction));

        Transactions result = transactionsService.getTransactionById(1);

        assertNotNull(result);
        assertEquals(transaction, result);
        verify(transactionsDAO, times(1)).findById(1);
    }

    

    @Test
    void testGetSuccessTransaction() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionsDAO.findByTransactionStatus(Transactions.TransactionStatus.SUCCESS)).thenReturn(transactions);

        List<Transactions> result = transactionsService.getSuccessTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
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
        when(transactionsDAO.findByCustomers_CustomerId(anyInt())).thenReturn(transactions);

        List<Transactions> result = transactionsService.getTransactionsByCustomerId(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
        verify(transactionsDAO, times(1)).findByCustomers_CustomerId(1);
    }

    @Test
    void testGetAllTransactions() {
        Transactions transaction = new Transactions();
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionsDAO.findAll()).thenReturn(transactions);

        List<Transactions> result = transactionsService.getAllTransactions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
        verify(transactionsDAO, times(1)).findAll();
    }
}
