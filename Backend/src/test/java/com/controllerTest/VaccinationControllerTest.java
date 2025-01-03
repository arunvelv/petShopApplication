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
 
    @InjectMocks //// Automatically injects the mocked `VaccinationsService` into `VaccinationsController`.
    private VaccinationsController vaccinationController;
 
    @Mock //// Mocks the service dependency to isolate the controller logic.
    private VaccinationsService vaccinationService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddVaccinations() {
    	// Arrange: Creates a new vaccination object for the test.
        Vaccinations vaccination = new Vaccinations();
        
     // Act: Calls the addVaccination method in the controller.
        ResponseEntity<?> response = vaccinationController.addVaccinations(vaccination);
 
        assertEquals("Vaccination added", response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
     // Assert: Verifies the response body and HTTP status.
        verify(vaccinationService, times(1)).addVaccinations(vaccination);
     // Verify: Ensures the `addVaccinations` method in the service is called exactly once.
    }
 
    @Test
    void testGetVaccinationsByIdFound() {
        Vaccinations vaccination = new Vaccinations();
     // Mocks the service to return the vaccination when called with any integer.

        when(vaccinationService.getById(anyInt())).thenReturn(vaccination);
        // Act: Calls the `getVaccinationsById` method with ID `1`.
        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);
 
     // Assert: Checks the response body matches the vaccination and the HTTP status is 200 (OK).

        assertEquals(vaccination, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
     // Verify: Ensures the service's `getById` method is called exactly once.
        verify(vaccinationService, times(1)).getById(1);
    }
 
    @Test
    void testGetVaccinationsByIdNotFound() {
    	// Arrange: Mocks the service to return null, simulating a not-found scenario.
        when(vaccinationService.getById(anyInt())).thenReturn(null);
     // Act: Calls the `getVaccinationsById` method with ID `1`.
        ResponseEntity<?> response = vaccinationController.getVaccinationsById(1);
 
     // Assert: Checks that the response body indicates "Not Found" and the HTTP status is 404.
        assertEquals("Vaccination Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
     // Verify: Ensures the service's `getById` method is called exactly once.
        verify(vaccinationService, times(1)).getById(1);
    }
 
    @Test
    void testGetAvailableVaccinations() {
        Vaccinations vaccination = new Vaccinations();
        // Arrange: Creates a list of available vaccinations.
     // Mocks the service to return the list of available vaccinations.
        List<Vaccinations> availableVaccinations = Arrays.asList(vaccination);
     // Act: Calls the `getAvailableVaccinations` method.
        when(vaccinationService.findAvailableVaccinations()).thenReturn(availableVaccinations);
 
        ResponseEntity<?> response = vaccinationController.getAvailableVaccinations();
     // Assert: Verifies the size of the returned list and the HTTP status is 200.
        assertEquals(1, ((List<?>) response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
     // Verify: Ensures the service's `findAvailableVaccinations` is called once.
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
 
    @Test
    void testUpdateVaccinationsFound() {
        Vaccinations updatedVaccination = new Vaccinations();
     // Mocks the service to return the updated vaccination.
        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(updatedVaccination);
     // Act: Calls the `updateVaccinations` method.
        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
     // Assert: Checks the response body and HTTP status.
        assertEquals("Vaccinations updated", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
     // Verify: Ensures the service's `updateVaccinations` is called once.
        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
    }
 
    @Test
    void testUpdateVaccinationsNotFound() {
        Vaccinations updatedVaccination = new Vaccinations();
        // Mocks the service to return null, simulating an update failure.
        when(vaccinationService.updateVaccinations(anyInt(), any())).thenReturn(null);
 
        ResponseEntity<?> response = vaccinationController.updateVaccinations(updatedVaccination, 1);
 
        assertEquals("Vaccination not found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vaccinationService, times(1)).updateVaccinations(1, updatedVaccination);
    }
 
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
 