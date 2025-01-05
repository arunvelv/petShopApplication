package com.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.*;
import com.model.Address;
import com.service.AddressService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
        address = new Address(1, "123 Main St", "New York", "NY", "10001");
    }

    @Test
    void testAddAddress() throws Exception {
        // Arrange
        when(addressService.addAddress(any(Address.class)))
                .thenReturn(new ResponseEntity<>(address, HttpStatus.CREATED));

        // Act & Assert
        mockMvc.perform(post("/api/v1/address/add")
                        .contentType("application/json")
                        .content("{ \"addressId\": 1, \"street\": \"123 Main St\", \"city\": \"New York\", \"state\": \"NY\", \"zipCode\": \"10001\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.addressId").value(1))
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("10001"));

        verify(addressService, times(1)).addAddress(any(Address.class)); // Ensure the service method was called
    }

    @Test
    void testGetAllAddresses() throws Exception {
        // Arrange
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        when(addressService.getAllAddresses()).thenReturn(new ResponseEntity<>(addressList, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(get("/api/v1/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].addressId").value(1))
                .andExpect(jsonPath("$[0].street").value("123 Main St"))
                .andExpect(jsonPath("$[0].city").value("New York"))
                .andExpect(jsonPath("$[0].state").value("NY"))
                .andExpect(jsonPath("$[0].zipCode").value("10001"));

        verify(addressService, times(1)).getAllAddresses(); // Ensure the service method was called
    }

    @Test
    void testUpdateAddress() throws Exception {
        // Arrange
        when(addressService.updateAddress(any(Address.class)))
                .thenReturn(new ResponseEntity<>(address, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(put("/api/v1/address/update/1")
                        .contentType("application/json")
                        .content("{ \"addressId\": 1, \"street\": \"123 Main St\", \"city\": \"New York\", \"state\": \"NY\", \"zipCode\": \"10001\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(1))
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("10001"));

        verify(addressService, times(1)).updateAddress(any(Address.class)); // Ensure the service method was called
    }

    @Test
    void testFindAddressByAddressId() throws Exception {
        // Arrange
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        when(addressService.findAddressByAddressId(1)).thenReturn(new ResponseEntity<>(addressList, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(get("/api/v1/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].addressId").value(1))
                .andExpect(jsonPath("$[0].street").value("123 Main St"))
                .andExpect(jsonPath("$[0].city").value("New York"))
                .andExpect(jsonPath("$[0].state").value("NY"))
                .andExpect(jsonPath("$[0].zipCode").value("10001"));

        verify(addressService, times(1)).findAddressByAddressId(1); // Ensure the service method was called
    }
}