package com.serviceTest;

import com.dao.AddressDAO;
import com.model.Address;
import com.service.AddressService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Use this to initialize mocks
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService; // Automatically inject mocks into this service

    @Mock
    private AddressDAO addressDAO; // Mock AddressDAO to be injected into AddressService

    private Address address;

    @BeforeEach
    void setUp() {
        // Initialize the address object for each test
        address = new Address(1, "123 Main St", "New York", "NY", "10001");
    }



    @Test
    void testAddAddress() {
        // Arrange
        when(addressDAO.save(address)).thenReturn(address);

        // Act
        ResponseEntity<Address> response = addressService.addAddress(address);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status should be CREATED");
        verify(addressDAO, times(1)).save(address); // Verify that save was called once
    }

    @Test
    void testGetAllAddresses() {
        // Arrange
        when(addressDAO.findAll()).thenReturn(Arrays.asList(address));

        // Act
        ResponseEntity<List<Address>> response = addressService.getAllAddresses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        assertFalse(response.getBody().isEmpty(), "Address list should not be empty");
        verify(addressDAO, times(1)).findAll(); // Verify that findAll was called once
    }

    @Test
    void testUpdateAddressFound() {
        // Arrange
        when(addressDAO.findById(address.getAddressId())).thenReturn(Optional.of(address));
        when(addressDAO.save(address)).thenReturn(address);

        // Act
        ResponseEntity<Address> response = addressService.updateAddress(address);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        verify(addressDAO, times(1)).findById(address.getAddressId()); // Verify that findById was called once
        verify(addressDAO, times(1)).save(address); // Verify that save was called once
    }

    @Test
    void testUpdateAddressNotFound() {
        // Arrange
        when(addressDAO.findById(address.getAddressId())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Address> response = addressService.updateAddress(address);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status should be NOT_FOUND");
        verify(addressDAO, times(1)).findById(address.getAddressId()); // Verify that findById was called once
        verify(addressDAO, times(0)).save(address); // Verify that save was not called
    }

    @Test
    void testFindAddressByAddressId() {
        // Arrange
        when(addressDAO.findByAddressId(address.getAddressId())).thenReturn(Arrays.asList(address));

        // Act
        ResponseEntity<List<Address>> response = addressService.findAddressByAddressId(address.getAddressId());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        assertFalse(response.getBody().isEmpty(), "Address list should not be empty");
        verify(addressDAO, times(1)).findByAddressId(address.getAddressId()); // Verify that findByAddressId was called once
    }
}
