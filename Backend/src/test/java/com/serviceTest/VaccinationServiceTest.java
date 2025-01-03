package com.serviceTest;

import com.dao.VaccinationsDAO;
import com.model.Vaccinations;
import com.service.VaccinationsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class VaccinationServiceTest {

    @InjectMocks
    private VaccinationsService vaccinationService;

    @Mock
    private VaccinationsDAO vaccinationDAO;

    private Vaccinations vaccination;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vaccination = new Vaccinations();
        vaccination.setVaccinationId(1);
        vaccination.setName("COVID-19 Vaccine");
        vaccination.setDescription("A vaccine to prevent COVID-19.");
        vaccination.setPrice(20);
        vaccination.setAvailable(true);
    }

    @Test
    void testAddVaccinations() {
        // Arrange
    	Vaccinations vaccination = new Vaccinations();

        // Act
        vaccinationService.addVaccinations(vaccination);

        // Assert
        verify(vaccinationDAO, times(1)).save(vaccination);
    }

    @Test
    void testGetByIdFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.of(vaccination));

        // Act
        Vaccinations result = vaccinationService.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(vaccination, result);
        verify(vaccinationDAO, times(1)).findById(1);
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Vaccinations result = vaccinationService.getById(1);

        // Assert
        assertNull(result);
        verify(vaccinationDAO, times(1)).findById(1);
    }

    @Test
    void testFindAvailableVaccinations() {
        // Arrange
       
        Vaccinations vaccination2 = new Vaccinations();
       
        vaccination2.setAvailable(true);
        List<Vaccinations> availableVaccinations = Arrays.asList(vaccination2);

        when(vaccinationDAO.findByAvailable(true)).thenReturn(availableVaccinations);

        // Act
        List<Vaccinations> result = vaccinationService.findAvailableVaccinations();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationDAO, times(1)).findByAvailable(true);
    }

    @Test
    void testFindUnavailableVaccinations() {
        // Arrange
        Vaccinations vaccination1 = new Vaccinations();
       
        vaccination1.setAvailable(false);
       
        List<Vaccinations> unavailableVaccinations = Arrays.asList(vaccination1);

        when(vaccinationDAO.findByAvailable(false)).thenReturn(unavailableVaccinations);

        // Act
        List<Vaccinations> result = vaccinationService.findUnavailableVaccinations();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationDAO, times(1)).findByAvailable(false);
    }

    @Test
    void testUpdateVaccinations() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.of(vaccination));
        Vaccinations updatedVaccination = new Vaccinations();
        updatedVaccination.setName("Updated COVID-19 Vaccine");
        updatedVaccination.setDescription("Updated description");
        updatedVaccination.setPrice(25);
        updatedVaccination.setAvailable(false);

        // Act
        Vaccinations result = vaccinationService.updateVaccinations(1, updatedVaccination);

        // Assert
        assertNotNull(result);
        assertEquals("Updated COVID-19 Vaccine", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertEquals(25.0, result.getPrice());
        assertFalse(result.isAvailable());
        verify(vaccinationDAO, times(1)).save(any(Vaccinations.class));
    }

    @Test
    void testUpdateVaccinationsNotFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.empty());
        Vaccinations updatedVaccination = new Vaccinations();

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vaccinationService.updateVaccinations(1, updatedVaccination));
        verify(vaccinationDAO, times(0)).save(any(Vaccinations.class));
    }

    @Test
    void testGetAllVaccinations() {
        // Arrange
        Vaccinations vaccination1 = new Vaccinations();
       
        List<Vaccinations> vaccinations = Arrays.asList(vaccination1);

        when(vaccinationDAO.findAll()).thenReturn(vaccinations);

        // Act
        List<Vaccinations> result = vaccinationService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationDAO, times(1)).findAll();
    }
}
