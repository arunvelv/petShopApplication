package com.serviceTest;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;
 
import com.dao.GroomingServiceDAO;

import com.model.GroomingService;

import com.service.GroomingServiceService;
 
public class GroomingServiceServicesTest {
 
    @Mock

    private GroomingServiceDAO groomingServiceDAO;
 
    @InjectMocks

    private GroomingServiceService groomingServiceService;
 
    GroomingServiceServicesTest() {

        MockitoAnnotations.openMocks(this);

    }
 
    @Test

    void testAddGroomingService_Success() {

        GroomingService service = new GroomingService();

        service.setName("Basic Grooming");
 
        when(groomingServiceDAO.save(service)).thenReturn(service);
 
        ResponseEntity<?> response = groomingServiceService.addGroomingService(service);
 
        assertEquals(201, response.getStatusCodeValue());

        assertEquals("Grooming service added successfully", response.getBody());

    }
 
    @Test

    void testAddGroomingService_InvalidInput() {

        ResponseEntity<?> response = groomingServiceService.addGroomingService(null);
 
        assertEquals(400, response.getStatusCodeValue());

        assertEquals("Invalid grooming service details", response.getBody());

    }
 
    @Test

    void testGetAllGroomingServices_EmptyList() {

        when(groomingServiceDAO.findAll()).thenReturn(new ArrayList<>());
 
        ResponseEntity<?> response = groomingServiceService.getAllGroomingServices();
 
        assertEquals(404, response.getStatusCodeValue());

        assertEquals("No grooming services found", response.getBody());

    }
 
    @Test

    void testGetAllGroomingServices_Success() {

        List<GroomingService> services = List.of(new GroomingService(), new GroomingService());
 
        when(groomingServiceDAO.findAll()).thenReturn(services);
 
        ResponseEntity<?> response = groomingServiceService.getAllGroomingServices();
 
        assertEquals(200, response.getStatusCodeValue());

        assertEquals(services, response.getBody());

    }
 
    @Test

    void testUpdateGroomingService_NotFound() {

        when(groomingServiceDAO.findById(1)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = groomingServiceService.updateGroomingService(1, new GroomingService());
 
        assertEquals(404, response.getStatusCodeValue());

        assertEquals("Grooming service not found", response.getBody());

    }
 
    @Test

    void testFindByName_NotFound() {

        when(groomingServiceDAO.findByName("Nonexistent")).thenReturn(new ArrayList<>());
 
        ResponseEntity<?> response = groomingServiceService.findByName("Nonexistent");
 
        assertEquals(404, response.getStatusCodeValue());

        assertEquals("No grooming services found with the given name", response.getBody());

    }
 
    @Test

    void testFindByAvailable_Found() {

        List<GroomingService> services = List.of(new GroomingService());

        when(groomingServiceDAO.findByAvailable(true)).thenReturn(services);
 
        ResponseEntity<?> response = groomingServiceService.findByAvailable(true);
 
        assertEquals(200, response.getStatusCodeValue());

        assertEquals(services, response.getBody());

    }

}

 