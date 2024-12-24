package com.controllerTest;

import com.controller.VaccinationsController;
import com.model.Vaccinations;
import com.service.VaccinationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class VaccinationControllerTest {

    @InjectMocks
    private VaccinationsController vaccinationController;

    @Mock
    private VaccinationsService vaccinationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddVaccinations() {
        Vaccinations vaccination = new Vaccinations();
        ResponseEntity<?> response = vaccinationController.addVaccinations(vaccination);

        assertEquals("Vaccination added", response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(vaccinationService, times(1)).addVaccinations(vaccination);
    }

    @Test
    void testGetVaccinationsByIdFound() {
        Vaccinations vaccination = new Vaccinations();
        when(vaccinationService.getById(anyInt())).thenReturn(vaccination);

        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);

        assertEquals(vaccination, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vaccinationService, times(1)).getById(1);
    }

    @Test
    void testGetVaccinationsByIdNotFound() {
        when(vaccinationService.getById(anyInt())).thenReturn(null);

        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);

        assertEquals("Vaccination Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vaccinationService, times(1)).getById(1);
    }

    @Test
    void testGetAvailableVaccinations() {
        Vaccinations vaccination = new Vaccinations();
        List<Vaccinations> availableVaccinations = Arrays.asList(vaccination);

        when(vaccinationService.findAvailableVaccinations()).thenReturn(availableVaccinations);

        ResponseEntity<?> response = vaccinationController.getAvailableVaccinations();

        assertEquals(1, ((List<?>) response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vaccinationService, times(1)).findAvailableVaccinations();
    }

    @Test
    void testGetUnavailableVaccinations() {
        Vaccinations vaccination = new Vaccinations();
        List<Vaccinations> unavailableVaccinations = Arrays.asList(vaccination);

        when(vaccinationService.findUnavailableVaccinations()).thenReturn(unavailableVaccinations);

        ResponseEntity<?> response = vaccinationController.getUnavailableVaccinations();

        assertEquals(1, ((List<?>) response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vaccinationService, times(1)).findUnavailableVaccinations();
    }

//    @Test
//    void testUpdateVaccinationsFound() {
//        Vaccinations updatedVaccination = new Vaccinations();
//        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(updatedVaccination);
//
//        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
//
//        assertEquals("Vaccinations updated", response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
//    }
//
//    @Test
//    void testUpdateVaccinationsNotFound() {
//        Vaccinations updatedVaccination = new Vaccinations();
//        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(null);
//
//        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
//
//        assertEquals("Vaccination not found", response.getBody());
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
//    }

    @Test
    void testGetAllVaccinations() {
        Vaccinations vaccination1 = new Vaccinations();
        Vaccinations vaccination2 = new Vaccinations();
        List<Vaccinations> vaccinations = Arrays.asList(vaccination1, vaccination2);

        when(vaccinationService.getAll()).thenReturn(vaccinations);

        ResponseEntity<?> response = vaccinationController.getAll();

        assertEquals(2, ((List<?>) response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vaccinationService, times(1)).getAll();
    }
}
