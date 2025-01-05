package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.GroomingService;
import com.model.Pets;

class GroomingServiceTest {

    private GroomingService groomingService;

    @BeforeEach
    void setUp() {
        List<Pets> petsList = new ArrayList<>();
        
        // Initialize GroomingService
        groomingService = new GroomingService();
        groomingService.setServiceId(1);
        groomingService.setName("Full Groom");
        groomingService.setDescription("Complete grooming service for pets");
        groomingService.setPrice(50.0f);
        groomingService.setAvailable(true);
        groomingService.setPets(petsList);
    }

    @Test
    void testGroomingServiceDefaultConstructor() {
        GroomingService defaultGroomingService = new GroomingService();
        
        assertEquals(0, defaultGroomingService.getServiceId());
        assertNull(defaultGroomingService.getName());
        assertNull(defaultGroomingService.getDescription());
        assertEquals(0.0f, defaultGroomingService.getPrice());
        assertEquals(false, defaultGroomingService.isAvailable());
        assertNull(defaultGroomingService.getPets());
    }

    @Test
    void testGroomingServiceParameterizedConstructor() {
        List<Pets> petsList = new ArrayList<>();
        GroomingService groomingServiceWithParams = new GroomingService(2, "Nail Trim", "Trimming the pet's nails", 25.0f, false, petsList);

        assertEquals(2, groomingServiceWithParams.getServiceId());
        assertEquals("Nail Trim", groomingServiceWithParams.getName());
        assertEquals("Trimming the pet's nails", groomingServiceWithParams.getDescription());
        assertEquals(25.0f, groomingServiceWithParams.getPrice());
        assertEquals(false, groomingServiceWithParams.isAvailable());
        assertEquals(petsList, groomingServiceWithParams.getPets());
    }

    @Test
    void testGroomingServiceGetters() {
        assertEquals(1, groomingService.getServiceId());
        assertEquals("Full Groom", groomingService.getName());
        assertEquals("Complete grooming service for pets", groomingService.getDescription());
        assertEquals(50.0f, groomingService.getPrice());
        assertEquals(true, groomingService.isAvailable());
        assertEquals(new ArrayList<>(), groomingService.getPets());
    }

    @Test
    void testGroomingServiceSetters() {
        List<Pets> newPetsList = new ArrayList<>();
        
        groomingService.setServiceId(3);
        groomingService.setName("Teeth Cleaning");
        groomingService.setDescription("Cleaning the pet's teeth");
        groomingService.setPrice(30.0f);
        groomingService.setAvailable(false);
        groomingService.setPets(newPetsList);

        assertEquals(3, groomingService.getServiceId());
        assertEquals("Teeth Cleaning", groomingService.getName());
        assertEquals("Cleaning the pet's teeth", groomingService.getDescription());
        assertEquals(30.0f, groomingService.getPrice());
        assertEquals(false, groomingService.isAvailable());
        assertEquals(newPetsList, groomingService.getPets());
    }
}