package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Pets;
import com.model.Vaccinations;

class VaccinationsTest {

    private Vaccinations vaccination;
    
    @BeforeEach
    void setUp() {
        List<Pets> petsList = new ArrayList<>();
        
        vaccination = new Vaccinations();
        vaccination.setVaccinationId(1);
        vaccination.setName("Rabies");
        vaccination.setDescription("Rabies vaccination for pets");
        vaccination.setPrice(20.0f);
        vaccination.setAvailable(true);
        vaccination.setPets(petsList);
    }

    @Test
    void testVaccinationsDefaultConstructor() {
        Vaccinations defaultVaccination = new Vaccinations();

        assertEquals(0, defaultVaccination.getVaccinationId());
        assertNull(defaultVaccination.getName());
        assertNull(defaultVaccination.getDescription());
        assertEquals(0.0f, defaultVaccination.getPrice());
        assertEquals(false, defaultVaccination.isAvailable());
        assertNull(defaultVaccination.getPets());
    }

    @Test
    void testVaccinationsGetters() {
        assertEquals(1, vaccination.getVaccinationId());
        assertEquals("Rabies", vaccination.getName());
        assertEquals("Rabies vaccination for pets", vaccination.getDescription());
        assertEquals(20.0f, vaccination.getPrice());
        assertEquals(true, vaccination.isAvailable());
        assertEquals(0, vaccination.getPets().size()); // Empty list
    }

    @Test
    void testVaccinationsSetters() {
        List<Pets> newPetsList = new ArrayList<>();
        
        vaccination.setVaccinationId(2);
        vaccination.setName("Parvovirus");
        vaccination.setDescription("Parvovirus vaccination for dogs");
        vaccination.setPrice(15.0f);
        vaccination.setAvailable(false);
        vaccination.setPets(newPetsList);

        assertEquals(2, vaccination.getVaccinationId());
        assertEquals("Parvovirus", vaccination.getName());
        assertEquals("Parvovirus vaccination for dogs", vaccination.getDescription());
        assertEquals(15.0f, vaccination.getPrice());
        assertEquals(false, vaccination.isAvailable());
        assertEquals(0, vaccination.getPets().size()); // Empty list
    }
}