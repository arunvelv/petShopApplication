package com.controllerTest;
 
 
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
 
import java.util.Arrays;

import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
 
import com.controller.SuppliersController;

import com.model.Address;

import com.model.Suppliers;

import com.service.SuppliersService;
 
class SupplierControllerTest {
 
    @InjectMocks

    private SuppliersController supplierController;
 
    @Mock

    private SuppliersService suppliersService;
 
    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

    }
 
    @Test

    void testGetAllSuppliers() {

        Address address1 = new Address(1, "Street1", "City1", "State1", "12345");

        Address address2 = new Address(2, "Street2", "City2", "State2", "67890");
 
        List<Suppliers> suppliersList = Arrays.asList(

            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address1),

            new Suppliers(2, "Supplier2", "Jane Doe", "0987654321", "email2@example.com", address2)

        );
 
        when(suppliersService.getAllSuppliers()).thenReturn(suppliersList);
 
        ResponseEntity<List<Suppliers>> response = supplierController.getAllSuppliers();
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, response.getBody().size());

        verify(suppliersService, times(1)).getAllSuppliers();

    }
 
//    @Test
//
//    void testGetCustomerById() {
//
//        Address address = new Address(1, "Street1", "City1", "State1", "12345");
//
//        Suppliers supplier = new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address);
// 
//        when(suppliersService.getSuppliersById(1)).thenReturn(supplier);
// 
//        ResponseEntity<Suppliers> response = supplierController.getCustomerById(1);
// 
//        assertNotNull(response);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertEquals(1, response.getBody().getSuppliersId());
//
//        verify(suppliersService, times(1)).getSuppliersById(1);
//
//    }
// 
//    @Test
//
//    void testGetCustomerById_NotFound() {
//
//        when(suppliersService.getSuppliersById(1)).thenReturn(null);
// 
//        ResponseEntity<Suppliers> response = supplierController.getCustomerById(1);
// 
//        assertNotNull(response);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//
//        verify(suppliersService, times(1)).getSuppliersById(1);
//
//    }
 
//    @Test
//
//    void testGetSuppliersByName() {
//
//        Address address = new Address(1, "Street1", "City1", "State1", "12345");
//
//        List<Suppliers> suppliersList = Arrays.asList(
//
//            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)
//
//        );
// 
//        when(suppliersService.getByName("Supplier1")).thenReturn(suppliersList);
// 
//        ResponseEntity<List<Suppliers>> response = supplierController.getsuppliersByName("Supplier1");
// 
//        assertNotNull(response);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertEquals(1, response.getBody().size());
//
//        verify(suppliersService, times(1)).getByName("Supplier1");
//
//    }
// 
    @Test

    void testGetByCity() {

        Address address = new Address(1, "Street1", "City1", "State1", "12345");

        List<Suppliers> suppliersList = Arrays.asList(

            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)

        );
 
        // Create a ResponseEntity explicitly as ResponseEntity<?>

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(suppliersList, HttpStatus.OK);
 
        // Use Mockito's type-safe stubbing

        when(suppliersService.findSupplierByCity("City1"))

            .thenAnswer(invocation -> expectedResponse);
 
        // Call the controller method

        ResponseEntity<?> response = supplierController.getByCity("City1");
 
        // Assertions

        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(suppliersList, response.getBody());

        verify(suppliersService, times(1)).findSupplierByCity("City1");

    }
 
 
 
    @Test

    void testGetByState() {

        Address address = new Address(1, "Street1", "City1", "State1", "12345");

        List<Suppliers> suppliersList = Arrays.asList(

            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)

        );

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(suppliersList, HttpStatus.OK);
 
        when(suppliersService.findSupplierByState("State1"))

           .thenAnswer(invocation -> expectedResponse);
 
        ResponseEntity<?> response = supplierController.getByState("State1");
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(suppliersList, response.getBody());

        verify(suppliersService, times(1)).findSupplierByState("State1");

    }
 
//    @Test

//    void testAddCustomer() {
//
//        Address address = new Address(1, "Street1", "City1", "State1", "12345");
//
//        Suppliers newSupplier = new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address);
// 
//        when(suppliersService.addSuppliers(newSupplier)).thenReturn(newSupplier);
// 
//        ResponseEntity<Suppliers> response = supplierController.addCustomer(newSupplier);
// 
//        assertNotNull(response);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        assertEquals("Supplier1", response.getBody().getName());
//
//        verify(suppliersService, times(1)).addSuppliers(newSupplier);

//    }
 
    @Test
    void testUpdateCustomer() {

        Address address = new Address(1, "Street1", "City1", "State1", "12345");

        Suppliers existingSupplier = new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address);

        Suppliers updatedSupplier = new Suppliers(1, "UpdatedSupplier", "Jane Doe", "9876543210", "updated@example.com", address);
 
        when(suppliersService.updateSuppliers(1, updatedSupplier)).thenReturn(updatedSupplier);
 
        ResponseEntity<Suppliers> response = supplierController.updateCustomer(1, updatedSupplier);
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("UpdatedSupplier", response.getBody().getName());

        verify(suppliersService, times(1)).updateSuppliers(1, updatedSupplier);

    }
 
    @Test

    void testUpdateCustomer_NotFound() {

        Suppliers updatedSupplier = new Suppliers();
 
        when(suppliersService.updateSuppliers(1, updatedSupplier)).thenReturn(null);
 
        ResponseEntity<Suppliers> response = supplierController.updateCustomer(1, updatedSupplier);
 
        assertNotNull(response);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(suppliersService, times(1)).updateSuppliers(1, updatedSupplier);

    }

}

 