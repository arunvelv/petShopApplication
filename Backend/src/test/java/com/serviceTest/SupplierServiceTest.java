package com.serviceTest;
 
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.dao.SuppliersDAO;
import com.model.Address;
import com.model.Suppliers;
import com.service.SuppliersService;
 
class SuppliersServiceTest {
 
    @InjectMocks
    private SuppliersService suppliersService;
 
    @Mock
    private SuppliersDAO suppliersDAO;
 
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
 
        when(suppliersDAO.findAll()).thenReturn(suppliersList);
 
        List<Suppliers> result = suppliersService.getAllSuppliers();
        assertEquals(2, result.size());
        verify(suppliersDAO, times(1)).findAll();
    }
 
    @Test
    void testAddSuppliers() {
        Address address = new Address(3, "Street3", "City3", "State3", "54321");
        Suppliers newSupplier = new Suppliers(3, "Supplier3", "John Smith", "1231231234", "email3@example.com", address);
 
        when(suppliersDAO.save(newSupplier)).thenReturn(newSupplier);
 
        Suppliers result = suppliersService.addSuppliers(newSupplier);
        assertNotNull(result);
        assertEquals("Supplier3", result.getName());
        verify(suppliersDAO, times(1)).save(newSupplier);
    }
 
    @Test
    void testGetSuppliersById() {
        Address address = new Address(1, "Street1", "City1", "State1", "12345");
        Suppliers supplier = new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address);
 
        when(suppliersDAO.findBySuppliersId(1)).thenReturn(supplier);
 
        Suppliers result = suppliersService.getSuppliersById(1);
        assertNotNull(result);
        assertEquals(1, result.getSuppliersId());
        verify(suppliersDAO, times(1)).findBySuppliersId(1);
    }
 
    @Test
    void testGetByName() {
        Address address = new Address(1, "Street1", "City1", "State1", "12345");
        List<Suppliers> suppliersList = Arrays.asList(
            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)
        );
 
        when(suppliersDAO.findByName("Supplier1")).thenReturn(suppliersList);
 
        List<Suppliers> result = suppliersService.getByName("Supplier1");
        assertEquals(1, result.size());
        assertEquals("Supplier1", result.get(0).getName());
        verify(suppliersDAO, times(1)).findByName("Supplier1");
    }
 
    @Test
    void testFindSupplierByCity() {
        Address address = new Address(1, "Street1", "City1", "State1", "12345");
        List<Suppliers> suppliersList = Arrays.asList(
            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)
        );
 
        when(suppliersDAO.findByCity("City1")).thenReturn(suppliersList);
 
        ResponseEntity<?> response = suppliersService.findSupplierByCity("City1");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(suppliersDAO, times(1)).findByCity("City1");
    }
 
    @Test
    void testFindSupplierByState() {
        Address address = new Address(1, "Street1", "City1", "State1", "12345");
        List<Suppliers> suppliersList = Arrays.asList(
            new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address)
        );
 
        when(suppliersDAO.findByState("State1")).thenReturn(suppliersList);
 
        ResponseEntity<?> response = suppliersService.findSupplierByState("State1");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(suppliersDAO, times(1)).findByState("State1");
    }
 
    @Test
    void testUpdateSuppliers() {
        Address address = new Address(1, "Street1", "City1", "State1", "12345");
        Suppliers existingSupplier = new Suppliers(1, "Supplier1", "John Doe", "1234567890", "email1@example.com", address);
        Suppliers updatedDetails = new Suppliers(1, "UpdatedSupplier", "Jane Smith", "9876543210", "updated@example.com", address);
 
        when(suppliersDAO.findBySuppliersId(1)).thenReturn(existingSupplier);
        when(suppliersDAO.save(existingSupplier)).thenReturn(updatedDetails);
 
        Suppliers result = suppliersService.updateSuppliers(1, updatedDetails);
        assertNotNull(result);
        assertEquals("UpdatedSupplier", result.getName());
        verify(suppliersDAO, times(1)).findBySuppliersId(1);
        verify(suppliersDAO, times(1)).save(existingSupplier);
    }
}
 
 