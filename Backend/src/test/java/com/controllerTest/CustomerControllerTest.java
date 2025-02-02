package com.controllerTest;

import com.controller.*;
import com.model.*;
import com.model.Transactions.TransactionStatus;
import com.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> mockCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetCustomerById_Found() {
        Customer mockCustomer = new Customer();
        when(customerService.getCustomerById(1)).thenReturn(mockCustomer);

        ResponseEntity<Customer> response = customerController.getCustomerById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomer, response.getBody());
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerService.getCustomerById(1)).thenReturn(null);

        ResponseEntity<Customer> response = customerController.getCustomerById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetCustomerByName() {
        List<Customer> mockCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getCustomerByName("John", "Doe")).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getCustomerByName("John", "Doe");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetCustomersByCity() {
        List<Customer> mockCustomers = Collections.singletonList(new Customer());
        when(customerService.getCustomersByCity("New York")).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getCustomersByCity("New York");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetCustomersByState() {
        List<Customer> mockCustomers = Collections.singletonList(new Customer());
        when(customerService.getCustomersByState("NY")).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getCustomersByState("NY");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetTransactionsByCustomerId() {
        List<Customer> mockTransactions = Collections.singletonList(new Customer());
        when(customerService.getTransactionsByCustomerId(1)).thenReturn(mockTransactions);

        ResponseEntity<List<Customer>> response = customerController.getTransactionsByCustomerId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockTransactions, response.getBody());
    }

    @Test
    void testGetCustomersByTransactionStatus() {
        List<Customer> mockCustomers = Collections.singletonList(new Customer());
        when(customerService.getCustomersByTransactionStatus(TransactionStatus.SUCCESS)).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getCustomersByTransactionStatus(TransactionStatus.SUCCESS);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetCustomersWithNoTransactions() {
        List<Customer> mockCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getCustomersWithNoTransactions()).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getCustomersWithNoTransactions();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomers, response.getBody());
    }

    @Test
    void testGetPetsByCustomerId() {
        List<Pets> mockPets = Arrays.asList(new Pets(), new Pets());
        when(customerService.getPetsByCustomerId(1)).thenReturn(mockPets);

        ResponseEntity<List<Pets>> response = customerController.getPetsByCustomerId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPets, response.getBody());
    }

    @Test
    void testAddCustomer() {
        // Create mock Address and Customer objects
        Address mockAddress = new Address();
        mockAddress.setAddressId(1);
        mockAddress.setStreet("123 Main St");
        mockAddress.setCity("New York");
        mockAddress.setState("NY");
        mockAddress.setZipCode("10001");

        Customer mockCustomer = new Customer();
        mockCustomer.setFirstName("John");
        mockCustomer.setLastName("Doe");
        mockCustomer.setEmail("john.doe@example.com");
        mockCustomer.setPhoneNumber("1234567890");

        // Create a CustomerPayload object with the mock Customer and Address
        CustomerPayload mockPayload = new CustomerPayload();
        mockPayload.setCustomer(mockCustomer);
        mockPayload.setAddress(mockAddress);

        // Mock the service layer behavior
        when(customerService.findByAddress(mockAddress.getAddressId())).thenReturn(Collections.emptyList());
        when(customerService.saveCustomer(mockCustomer, mockAddress)).thenReturn(mockCustomer);

        // Call the controller method directly
        ResponseEntity<Customer> response = customerController.addCustomer(mockPayload);

        // Assert the response status and body
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
    }



    @Test
    void testUpdateCustomer_Found() {
        Customer mockCustomer = new Customer();
        when(customerService.updateCustomer(eq(1), any(Customer.class))).thenReturn(mockCustomer);

        ResponseEntity<Customer> response = customerController.updateCustomer(1, mockCustomer);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCustomer, response.getBody());
    }

    @Test
    void testUpdateCustomer_NotFound() {
        when(customerService.updateCustomer(eq(1), any(Customer.class))).thenReturn(null);

        ResponseEntity<Customer> response = customerController.updateCustomer(1, new Customer());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}