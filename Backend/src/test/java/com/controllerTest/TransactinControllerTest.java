package com.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.controller.TransactionsController;
import com.model.Transactions;
import com.service.TransactionsService;

//import com.controller.VaccinationsController;
//import com.model.Vaccinations;
//import com.service.VaccinationsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//class VaccinationControllerTest {
//
//    @InjectMocks
//    private VaccinationsController vaccinationController;
//
//    @Mock
//    private VaccinationsService vaccinationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddVaccinations() {
//        Vaccinations vaccination = new Vaccinations();
//        ResponseEntity<?> response = vaccinationController.addVaccinations(vaccination);
//
//        assertEquals("Vaccination added", response.getBody());
//        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//        verify(vaccinationService, times(1)).addVaccinations(vaccination);
//    }
//
//    @Test
//    void testGetVaccinationsByIdFound() {
//        Vaccinations vaccination = new Vaccinations();
//        when(vaccinationService.getById(anyInt())).thenReturn(vaccination);
//
//        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);
//
//        assertEquals(vaccination, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(vaccinationService, times(1)).getById(1);
//    }
//
////    @Test
////    void testGetVaccinationsByIdNotFound() {
////        when(vaccinationService.getById(anyInt())).thenReturn(null);
////
////        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);
////
////        assertEquals("Vaccination Not Found", response.getBody());
////        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
////        verify(vaccinationService, times(1)).getById(1);
////    }
//
//    @Test
//    void testGetAvailableVaccinations() {
//        Vaccinations vaccination = new Vaccinations();
//        List<Vaccinations> availableVaccinations = Arrays.asList(vaccination);
//
//        when(vaccinationService.findAvailableVaccinations()).thenReturn(availableVaccinations);
//
//        ResponseEntity<?> response = vaccinationController.getAvailableVaccinations();
//
//        assertEquals(1, ((List<?>) response.getBody()).size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(vaccinationService, times(1)).findAvailableVaccinations();
//    }
//
//    @Test
//    void testGetUnavailableVaccinations() {
//        Vaccinations vaccination = new Vaccinations();
//        List<Vaccinations> unavailableVaccinations = Arrays.asList(vaccination);
//
//        when(vaccinationService.findUnavailableVaccinations()).thenReturn(unavailableVaccinations);
//
//        ResponseEntity<?> response = vaccinationController.getUnavailableVaccinations();
//
//        assertEquals(1, ((List<?>) response.getBody()).size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(vaccinationService, times(1)).findUnavailableVaccinations();
//    }
//
////    @Test
////    void testUpdateVaccinationsFound() {
////        Vaccinations updatedVaccination = new Vaccinations();
////        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(updatedVaccination);
////
////        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
////
////        assertEquals("Vaccinations updated", response.getBody());
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
////    }
////
////    @Test
////    void testUpdateVaccinationsNotFound() {
////        Vaccinations updatedVaccination = new Vaccinations();
////        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(null);
////
////        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
////
////        assertEquals("Vaccination not found", response.getBody());
////        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
////        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
////    }
//
//    @Test
//    void testGetAllVaccinations() {
//        Vaccinations vaccination1 = new Vaccinations();
//        Vaccinations vaccination2 = new Vaccinations();
//        List<Vaccinations> vaccinations = Arrays.asList(vaccination1, vaccination2);
//
//        when(vaccinationService.getAll()).thenReturn(vaccinations);
//
//        ResponseEntity<?> response = vaccinationController.getAll();
//
//        assertEquals(2, ((List<?>) response.getBody()).size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(vaccinationService, times(1)).getAll();
//    }
//}





class TransactinControllerTest {

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

//    @Test
//    void testGetTransactionsByIdFound() {
//        Transactions transaction = new Transactions();
//        when(transactionService.getTransactionById(anyInt())).thenReturn(transaction);
//
//        ResponseEntity<Transactions> response = (ResponseEntity<Transactions>)transactionsController.getTransactionById(1);
//
//        assertEquals(transaction, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(transactionService, times(1)).getTransactionById(1);
//    }


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

