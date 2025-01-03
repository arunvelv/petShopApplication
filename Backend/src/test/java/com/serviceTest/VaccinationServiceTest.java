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

    @InjectMocks// used to inject mocks
    private VaccinationsService vaccinationService;

    @Mock //create a mock object
    private VaccinationsDAO vaccinationDAO;

    private Vaccinations vaccination;

    @BeforeEach //runs before each tests
    void setUp() {
        MockitoAnnotations.openMocks(this);//initialize mock
        vaccination = new Vaccinations();
        vaccination.setVaccinationId(1);
        vaccination.setName("COVID-19 Vaccine");
        vaccination.setDescription("A vaccine to prevent COVID-19.");
        vaccination.setPrice(20);
        vaccination.setAvailable(true);
    }

    @Test
    void testAddVaccinations() //to ensure DAO's save method is run correctly.
    {
        // Arrange //creates an new vaccination object
    	Vaccinations vaccination = new Vaccinations();

        // Act // calls the addvaccinations on the service
        vaccinationService.addVaccinations(vaccination);

        // Assert //Verifies that save() on the DAO is called exactly once with the given vaccination.

        verify(vaccinationDAO, times(1)).save(vaccination);
    }

    @Test
    void testGetByIdFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.of(vaccination));//Mock DAO to return an existing object.

        // Act
        Vaccinations result = vaccinationService.getById(1); //Call the service method to fetch a vaccination by ID.

        // Assert
        assertNotNull(result);//Ensure the result is not null.
        assertEquals(vaccination, result);// Verify the returned object matches the mocked one.
        verify(vaccinationDAO, times(1)).findById(1);//Ensure the DAO is queried once with the correct ID.
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.empty());//Mock DAO to simulate no record found.

        // Act
        Vaccinations result = vaccinationService.getById(1);

        // Assert
        assertNull(result);
        verify(vaccinationDAO, times(1)).findById(1);
    }

    @Test
    void testFindAvailableVaccinations() {
        // Arrange
       
        Vaccinations vaccination2 = new Vaccinations();//Create a sample available vaccination.
       
        vaccination2.setAvailable(true);
        List<Vaccinations> availableVaccinations = Arrays.asList(vaccination2);//Mock a list of available vaccinations.

        when(vaccinationDAO.findByAvailable(true)).thenReturn(availableVaccinations);//Mock DAO to return the list.


        // Act
        List<Vaccinations> result = vaccinationService.findAvailableVaccinations();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());//Check the size of the returned list.
        verify(vaccinationDAO, times(1)).findByAvailable(true);
    }
    //Why Test? To confirm the service correctly filters available vaccinations.

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
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.of(vaccination));//Mock DAO to find an existing object.
        Vaccinations updatedVaccination = new Vaccinations();//Create an updated object.
        updatedVaccination.setName("Updated COVID-19 Vaccine"); 
        updatedVaccination.setDescription("Updated description");
        updatedVaccination.setPrice(25);
        updatedVaccination.setAvailable(false);

        // Act
        Vaccinations result = vaccinationService.updateVaccinations(1, updatedVaccination);//Call updateVaccinations() with the ID and updated vaccination data.

        // Assert
        assertNotNull(result);
        assertEquals("Updated COVID-19 Vaccine", result.getName());//Check updated name.
        assertEquals("Updated description", result.getDescription());
        assertEquals(25.0, result.getPrice());
        assertFalse(result.isAvailable());
        verify(vaccinationDAO, times(1)).save(any(Vaccinations.class));
    }

    @Test
    void testUpdateVaccinationsNotFound() {
        // Arrange
        when(vaccinationDAO.findById(anyInt())).thenReturn(Optional.empty());//Mock DAO to simulate a missing record.returns empty.

        Vaccinations updatedVaccination = new Vaccinations();// Create an updated object.

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vaccinationService.updateVaccinations(1, updatedVaccination));//Ensure an exception is thrown.
        verify(vaccinationDAO, times(0)).save(any(Vaccinations.class));//Ensure DAO's save method is not called.
    }

    @Test
    void testGetAllVaccinations() {
        // Arrange
        Vaccinations vaccination1 = new Vaccinations();//Create a sample vaccination.
       
        List<Vaccinations> vaccinations = Arrays.asList(vaccination1);// Mock a list of vaccinations.

        when(vaccinationDAO.findAll()).thenReturn(vaccinations);//Mock DAO behavior and returns a list.

        // Act
        List<Vaccinations> result = vaccinationService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vaccinationDAO, times(1)).findAll();
    }
}
