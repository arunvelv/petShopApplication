package com.serviceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.dao.AddressDAO;
import com.dao.SuppliersDAO;
import com.exception.InvalidInputException;
import com.model.Address;
import com.model.Suppliers;
import com.service.SuppliersService;

public class SupplierServiceTest {

    @InjectMocks
    private SuppliersService suppliersService;

    @Mock
    private SuppliersDAO suppliersDAO;

    @Mock
    private AddressDAO addressDAO;

    private Suppliers supplier;
    private Address address;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        address = new Address();
        address.setAddressId(1);
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZipCode("12345");

        supplier = new Suppliers(1, "Supplier Name", "John Doe", "1234567890", "supplier@example.com", address);
    }

    @Test
    public void testGetAllSuppliers() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(supplier);

        when(suppliersDAO.findAll()).thenReturn(suppliersList);

        List<Suppliers> result = suppliersService.getAllSuppliers();

        assertEquals(1, result.size());
        verify(suppliersDAO, times(1)).findAll();
    }

    @Test
    public void testAddSuppliers() {
        when(suppliersDAO.save(supplier)).thenReturn(supplier);

        Suppliers result = suppliersService.addSuppliers(supplier);

        assertEquals(supplier, result);
        verify(suppliersDAO, times(1)).save(supplier);
    }

    @Test
    public void testGetSuppliersById() {
        when(suppliersDAO.findBySuppliersId(1)).thenReturn(supplier);

        Suppliers result = suppliersService.getSuppliersById(1);

        assertEquals(supplier, result);
        verify(suppliersDAO, times(1)).findBySuppliersId(1);
    }

    @Test
    public void testFindSuppliersByName() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(supplier);

        when(suppliersDAO.findByName("Supplier Name")).thenReturn(suppliersList);

        List<Suppliers> result = suppliersService.findSuppliersByName("Supplier Name");

        assertEquals(1, result.size());
        verify(suppliersDAO, times(1)).findByName("Supplier Name");
    }

    @Test
    public void testFindSupplierByCity() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(supplier);

        when(suppliersDAO.findByCity("Springfield")).thenReturn(suppliersList);

        ResponseEntity<?> result = suppliersService.findSupplierByCity("Springfield");

        assertEquals(200, result.getStatusCodeValue());
        verify(suppliersDAO, times(1)).findByCity("Springfield");
    }


    @Test
    public void testSaveSuppliers_InvalidInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            suppliersService.saveSuppliers(null, null);
        });

        assertEquals("Suppliers or Address cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateSuppliers() {
        when(suppliersDAO.findBySuppliersId(1)).thenReturn(supplier);
        when(suppliersDAO.save(supplier)).thenReturn(supplier);

        Suppliers updatedSupplier = new Suppliers();
        updatedSupplier.setName("Updated Name");
        updatedSupplier.setContactPerson("Updated Person");
        updatedSupplier.setPhoneNumber("9876543210");
        updatedSupplier.setEmail("updated@example.com");
        updatedSupplier.setAddress(address);

        Suppliers result = suppliersService.updateSuppliers(1, updatedSupplier);

        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Person", result.getContactPerson());
        assertEquals("9876543210", result.getPhoneNumber());
        assertEquals("updated@example.com", result.getEmail());
        verify(suppliersDAO, times(1)).findBySuppliersId(1);
        verify(suppliersDAO, times(1)).save(supplier);
    }

    @Test
    public void testUpdateSuppliers_NotFound() {
        when(suppliersDAO.findBySuppliersId(1)).thenReturn(null);

        Suppliers updatedSupplier = new Suppliers();
        updatedSupplier.setName("Updated Name");

        Suppliers result = suppliersService.updateSuppliers(1, updatedSupplier);

        assertNull(result);
        verify(suppliersDAO, times(1)).findBySuppliersId(1);
        verify(suppliersDAO, times(0)).save(any(Suppliers.class));
    }
}
