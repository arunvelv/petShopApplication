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
 
import com.controller.AddressController;

import com.model.Address;

import com.service.AddressService;
 
class AddressControllerTest {
 
    @Mock

    private AddressService addressService;
 
    @InjectMocks

    private AddressController addressController;
 
    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

    }
 
    @Test

    void testAddAddress() {

        Address address = new Address(1, "123 Main St", "City1", "State1", "12345");
 
        when(addressService.addAddress(address)).thenReturn(new ResponseEntity<>(address, HttpStatus.CREATED));
 
        ResponseEntity<Address> response = addressController.addAddress(address);
 
        assertNotNull(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals("Address added successfully", response.getBody());

        verify(addressService, times(1)).addAddress(address);

    }
 
    @Test

    void testGetAllAddresses() {

        List<Address> addressList = Arrays.asList(

            new Address(1, "123 Main St", "City1", "State1", "12345"),

            new Address(2, "456 Elm St", "City2", "State2", "67890")

        );
 
        when(addressService.getAllAddresses()).thenReturn(new ResponseEntity<>(addressList, HttpStatus.OK));
 
        ResponseEntity<List<Address>> response = addressController.getAllAddresses();
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(addressList, response.getBody());

        verify(addressService, times(1)).getAllAddresses();

    }

//    @Test
//    void testUpdateAddress() {
//        // Mock data
//        Address updatedAddress = new Address(1, "789 Oak St", "City3", "State3", "54321");
//        ResponseEntity<String> mockResponse = new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
//
//        // Mock the service call
//        when(addressService.updateAddress(1, updatedAddress)).thenReturn(mockResponse);
//
//        // Call the controller method
//        ResponseEntity<Address> response = addressController.updateAddress(1, updatedAddress);
//
//        // Assertions
//        assertNotNull(response); // Ensure the response is not null
//        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check the status code
//        assertEquals("Address updated successfully", response.getBody()); // Check the response body
//
//        // Verify the service method was called once with the correct arguments
//        verify(addressService, times(1)).updateAddress(1, updatedAddress);
//    }

    @Test

    void testFindAddressByAddressId() {

        List<Address> addressList = Arrays.asList(

            new Address(1, "123 Main St", "City1", "State1", "12345")

        );
 
        when(addressService.findAddressByAddressId(1)).thenReturn(new ResponseEntity<>(addressList, HttpStatus.OK));
 
        ResponseEntity<List<Address>> response = addressController.findAddressByAddressId(1);
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(addressList, response.getBody());

        verify(addressService, times(1)).findAddressByAddressId(1);

    }

}
 
 