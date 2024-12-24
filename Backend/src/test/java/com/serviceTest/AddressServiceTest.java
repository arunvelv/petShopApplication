package com.serviceTest;
 
 
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
 
import java.util.Arrays;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
 
import com.dao.AddressDAO;

import com.model.Address;

import com.service.AddressService;
 
class AddressServiceTest {
 
    @Mock

    private AddressDAO addressDAO;
 
    @InjectMocks

    private AddressService addressService;
 
    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

    }
 
    @Test

    void testAddAddress() {

        Address address = new Address(1, "123 Main St", "City1", "State1", "12345");
 
        when(addressDAO.save(address)).thenReturn(address);
 
        ResponseEntity<String> response = addressService.addAddress(address);
 
        assertNotNull(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals("Address added successfully", response.getBody());

        verify(addressDAO, times(1)).save(address);

    }
 
    @Test

    void testGetAllAddresses() {

        List<Address> addressList = Arrays.asList(

            new Address(1, "123 Main St", "City1", "State1", "12345"),

            new Address(2, "456 Elm St", "City2", "State2", "67890")

        );
 
        when(addressDAO.findAll()).thenReturn(addressList);
 
        ResponseEntity<List<Address>> response = addressService.getAllAddresses();
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(addressList, response.getBody());

        verify(addressDAO, times(1)).findAll();

    }
 
    @Test

    void testUpdateAddressSuccess() {

        Address existingAddress = new Address(1, "123 Main St", "City1", "State1", "12345");

        Address updatedAddress = new Address(1, "789 Oak St", "City3", "State3", "54321");
 
        when(addressDAO.findById(existingAddress.getAddressId())).thenReturn(Optional.of(existingAddress));

        when(addressDAO.save(updatedAddress)).thenReturn(updatedAddress);
 
        ResponseEntity<String> response = addressService.updateAddress(updatedAddress);
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Address updated successfully", response.getBody());

        verify(addressDAO, times(1)).findById(existingAddress.getAddressId());

        verify(addressDAO, times(1)).save(updatedAddress);

    }
 
    @Test

    void testUpdateAddressNotFound() {

        Address updatedAddress = new Address(1, "789 Oak St", "City3", "State3", "54321");
 
        when(addressDAO.findById(updatedAddress.getAddressId())).thenReturn(Optional.empty());
 
        ResponseEntity<String> response = addressService.updateAddress(updatedAddress);
 
        assertNotNull(response);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("Address not found", response.getBody());

        verify(addressDAO, times(1)).findById(updatedAddress.getAddressId());

        verify(addressDAO, never()).save(updatedAddress);

    }
 
    @Test

    void testFindAddressByAddressId() {

        List<Address> addressList = Arrays.asList(

            new Address(1, "123 Main St", "City1", "State1", "12345")

        );
 
        when(addressDAO.findByAddressId(1)).thenReturn(addressList);
 
        ResponseEntity<List<Address>> response = addressService.findAddressByAddressId(1);
 
        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(addressList, response.getBody());

        verify(addressDAO, times(1)).findByAddressId(1);

    }

}
 
 