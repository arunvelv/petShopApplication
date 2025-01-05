package com.controllerTest;

import com.controller.SuppliersController;
import com.exception.InvalidInputException;
import com.model.Address;
import com.model.SupplierPayload;
import com.model.Suppliers;
import com.service.SuppliersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SuppliersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SuppliersService suppliersService;

    @InjectMocks
    private SuppliersController suppliersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(suppliersController).build();
    }

    @Test
    void testGetAllSuppliers() throws Exception {
        // Mock data
        Suppliers supplier1 = new Suppliers(1, "Supplier A", "John Doe", "1234567890", "supplierA@example.com", null);
        Suppliers supplier2 = new Suppliers(2, "Supplier B", "Jane Smith", "0987654321", "supplierB@example.com", null);
        List<Suppliers> suppliersList = Arrays.asList(supplier1, supplier2);

        // Mock service response
        when(suppliersService.getAllSuppliers()).thenReturn(suppliersList);

        // Perform GET request
        mockMvc.perform(get("/api/v1/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Supplier A"))
                .andExpect(jsonPath("$[1].name").value("Supplier B"));

        // Verify service interaction
        verify(suppliersService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetSuppliersById() throws Exception {
        // Mock data
        Suppliers supplier = new Suppliers(1, "Supplier A", "John Doe", "1234567890", "supplierA@example.com", null);

        // Mock service response
        when(suppliersService.getSuppliersById(1)).thenReturn(supplier);

        // Perform GET request
        mockMvc.perform(get("/api/v1/suppliers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Supplier A"));

        // Verify service interaction
        verify(suppliersService, times(1)).getSuppliersById(1);
    }

    @Test
    void testAddSuppliers() throws Exception {
        // Mocking the Address
        Address mockAddress = new Address();
        mockAddress.setAddressId(1);
        mockAddress.setCity("Mock City");
        mockAddress.setState("Mock State");

        // Mocking the Suppliers
        Suppliers mockSuppliers = new Suppliers();
        mockSuppliers.setSuppliersId(1);
        mockSuppliers.setName("Supplier A");
        mockSuppliers.setContactPerson("John Doe");
        mockSuppliers.setPhoneNumber("1234567890");
        mockSuppliers.setEmail("supplierA@example.com");
        mockSuppliers.setAddress(mockAddress);

        // Mocking the service methods
        when(suppliersService.findByAddress(1)).thenReturn(Arrays.asList());
        when(suppliersService.saveSuppliers(any(Suppliers.class), any(Address.class))).thenReturn(mockSuppliers);

        // Payload for the test
        String payload = "{ \"suppliers\": { \"name\": \"Supplier A\", \"contactPerson\": \"John Doe\", \"phoneNumber\": \"1234567890\", \"email\": \"supplierA@example.com\" }, \"address\": { \"addressId\": 1, \"city\": \"Mock City\", \"state\": \"Mock State\" } }";

        // Perform POST request and expect HTTP 200
        mockMvc.perform(post("/api/v1/suppliers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(jsonPath("$.name").value("Supplier A")) // Verify the name
                .andExpect(jsonPath("$.contactPerson").value("John Doe")) // Verify the contact person
                .andExpect(jsonPath("$.phoneNumber").value("1234567890")) // Verify the phone number
                .andExpect(jsonPath("$.email").value("supplierA@example.com")) // Verify the email
                .andExpect(jsonPath("$.address.city").value("Mock City")) // Verify the address city
                .andExpect(jsonPath("$.address.state").value("Mock State")); // Verify the address state

        // Verify service calls
        verify(suppliersService, times(1)).findByAddress(1);
        verify(suppliersService, times(1)).saveSuppliers(any(Suppliers.class), any(Address.class));
    }

    @Test
    void testAddSuppliersWithExistingSuppliers() throws Exception {
        // Mock service to simulate existing suppliers
        Address mockAddress = new Address();
        mockAddress.setAddressId(1);

        Suppliers existingSupplier = new Suppliers();
        existingSupplier.setSuppliersId(2);
        existingSupplier.setName("Existing Supplier");
        existingSupplier.setAddress(mockAddress);

        when(suppliersService.findByAddress(1)).thenReturn(Arrays.asList(existingSupplier));

        Suppliers mockSuppliers = new Suppliers();
        mockSuppliers.setName("Supplier A");
        mockSuppliers.setContactPerson("John Doe");
        mockSuppliers.setPhoneNumber("1234567890");
        mockSuppliers.setEmail("supplierA@example.com");
        mockSuppliers.setAddress(mockAddress);

        when(suppliersService.saveSuppliers(any(Suppliers.class), any(Address.class))).thenReturn(mockSuppliers);

        // Payload for the test
        String payload = "{ \"suppliers\": { \"name\": \"Supplier A\", \"contactPerson\": \"John Doe\", \"phoneNumber\": \"1234567890\", \"email\": \"supplierA@example.com\" }, \"address\": { \"addressId\": 1, \"city\": \"Mock City\", \"state\": \"Mock State\" } }";

        // Perform POST request and expect HTTP 200
        mockMvc.perform(post("/api/v1/suppliers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(jsonPath("$.name").value("Supplier A")) // Verify the name
                .andExpect(jsonPath("$.contactPerson").value("John Doe")) // Verify the contact person
                .andExpect(jsonPath("$.phoneNumber").value("1234567890")) // Verify the phone number
                .andExpect(jsonPath("$.email").value("supplierA@example.com")); // Verify the email

        // Verify that the service methods were called as expected
        verify(suppliersService, times(1)).findByAddress(1);
        verify(suppliersService, times(1)).saveSuppliers(any(Suppliers.class), any(Address.class));
    }




    @Test
    void testUpdateCustomer() throws Exception {
        // Mock data
        Suppliers supplier = new Suppliers(1, "Supplier A Updated", "John Doe", "1234567890", "supplierA@example.com", null);

        // Mock service response
        when(suppliersService.updateSuppliers(eq(1), any(Suppliers.class))).thenReturn(supplier);

        // Perform PUT request
        mockMvc.perform(put("/api/v1/suppliers/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Supplier A Updated\", \"contactPerson\": \"John Doe\", \"phoneNumber\": \"1234567890\", \"email\": \"supplierA@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Supplier A Updated"));

        // Verify service interaction
        verify(suppliersService, times(1)).updateSuppliers(eq(1), any(Suppliers.class));
    }

    @Test
    void testUpdateCustomerNotFound() throws Exception {
        // Mock service response
        when(suppliersService.updateSuppliers(eq(1), any(Suppliers.class))).thenReturn(null);

        // Perform PUT request
        mockMvc.perform(put("/api/v1/suppliers/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Supplier A Updated\", \"contactPerson\": \"John Doe\", \"phoneNumber\": \"1234567890\", \"email\": \"supplierA@example.com\" }"))
                .andExpect(status().isNotFound());

        // Verify service interaction
        verify(suppliersService, times(1)).updateSuppliers(eq(1), any(Suppliers.class));
    }
}
