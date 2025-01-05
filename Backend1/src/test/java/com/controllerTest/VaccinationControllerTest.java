package com.controllerTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;

import com.controller.VaccinationsController;
import com.model.Vaccinations;
import com.service.VaccinationsService;

import java.util.*;

class VaccinationsControllerTest {

    @Mock
    private VaccinationsService vaccinationsService;

    private MockMvc mockMvc;
    
    @InjectMocks
    private VaccinationsController vaccinationsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vaccinationsController).build();
    }

    @Test
    void testAddVaccinations() throws Exception {
        Vaccinations newVaccination = new Vaccinations();
        newVaccination.setName("Rabies");
        newVaccination.setDescription("Rabies vaccine for pets");
        newVaccination.setPrice(20.0f);
        newVaccination.setAvailable(true);

        // Mocking service using doNothing() since addVaccinations is a void method
        doNothing().when(vaccinationsService).addVaccinations(any(Vaccinations.class));

        // Perform POST request to add vaccination
        mockMvc.perform(post("/api/v1/vaccinations/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Rabies\",\"description\":\"Rabies vaccine for pets\",\"price\":20.0,\"available\":true}"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Vaccination added"));

        // Verify the service method was called once
        verify(vaccinationsService, times(1)).addVaccinations(any(Vaccinations.class));
    }


    @Test
    void testGetVaccinationsById() throws Exception {
        Vaccinations vaccination = new Vaccinations();
        vaccination.setVaccinationId(1);
        vaccination.setName("Rabies");
        vaccination.setDescription("Rabies vaccine for pets");
        vaccination.setPrice(20.0f);
        vaccination.setAvailable(true);

        // Mocking service
        when(vaccinationsService.getById(1)).thenReturn(vaccination);

        // Perform GET request to retrieve vaccination by ID
        mockMvc.perform(get("/api/v1/vaccinations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rabies"));

        verify(vaccinationsService, times(1)).getById(1);
    }

    @Test
    void testGetVaccinationsByIdNotFound() throws Exception {
        // Mocking service to return null for non-existing vaccination
        when(vaccinationsService.getById(999)).thenReturn(null);

        // Perform GET request with non-existent ID
        mockMvc.perform(get("/api/v1/vaccinations/999"))
                .andExpect(status().isNotFound());

        verify(vaccinationsService, times(1)).getById(999);
    }

    @Test
    void testGetAvailableVaccinations() throws Exception {
        List<Vaccinations> availableVaccinations = Arrays.asList(
                new Vaccinations(1, "Rabies", "Rabies vaccine for pets", 20.0f, true, null),
                new Vaccinations(2, "Distemper", "Distemper vaccine for pets", 15.0f, true, null)
        );

        // Mocking service to return available vaccinations
        when(vaccinationsService.findAvailableVaccinations()).thenReturn(availableVaccinations);

        // Perform GET request to retrieve available vaccinations
        mockMvc.perform(get("/api/v1/vaccinations/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(vaccinationsService, times(1)).findAvailableVaccinations();
    }

    @Test
    void testGetUnavailableVaccinations() throws Exception {
        List<Vaccinations> unavailableVaccinations = Arrays.asList(
                new Vaccinations(3, "Hepatitis", "Hepatitis vaccine for pets", 25.0f, false, null)
        );

        // Mocking service to return unavailable vaccinations
        when(vaccinationsService.findUnavailableVaccinations()).thenReturn(unavailableVaccinations);

        // Perform GET request to retrieve unavailable vaccinations
        mockMvc.perform(get("/api/v1/vaccinations/unavailable"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(vaccinationsService, times(1)).findUnavailableVaccinations();
    }

    @Test
    void testUpdateVaccinations() throws Exception {
        Vaccinations updatedVaccination = new Vaccinations();
        updatedVaccination.setName("Updated Rabies");
        updatedVaccination.setDescription("Updated description of Rabies vaccine");
        updatedVaccination.setPrice(25.0f);
        updatedVaccination.setAvailable(true);

        // Mocking service to return the updated vaccination
        doReturn(updatedVaccination).when(vaccinationsService).updateVaccinations(eq(1), any(Vaccinations.class));

        // Perform PUT request to update vaccination
        mockMvc.perform(put("/api/v1/vaccinations/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Rabies\",\"description\":\"Updated description of Rabies vaccine\",\"price\":25.0,\"available\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Vaccinations updated"));

        verify(vaccinationsService, times(1)).updateVaccinations(eq(1), any(Vaccinations.class));
    }

    @Test
    void testUpdateVaccinationsNotFound() throws Exception {
        Vaccinations updatedVaccination = new Vaccinations();
        updatedVaccination.setName("Updated Rabies");
        updatedVaccination.setDescription("Updated description of Rabies vaccine");
        updatedVaccination.setPrice(25.0f);
        updatedVaccination.setAvailable(true);

        // Mocking service to simulate "not found" behavior
        doReturn(null).when(vaccinationsService).updateVaccinations(eq(999), any(Vaccinations.class));

        // Perform PUT request to update vaccination that doesn't exist
        mockMvc.perform(put("/api/v1/vaccinations/update/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Rabies\",\"description\":\"Updated description of Rabies vaccine\",\"price\":25.0,\"available\":true}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Vaccination not found"));

        verify(vaccinationsService, times(1)).updateVaccinations(eq(999), any(Vaccinations.class));
    }
    @Test
    void testGetAllVaccinations() throws Exception {
        List<Vaccinations> vaccinationsList = Arrays.asList(
                new Vaccinations(1, "Rabies", "Rabies vaccine for pets", 20.0f, true, null),
                new Vaccinations(2, "Distemper", "Distemper vaccine for pets", 15.0f, true, null)
        );

        // Mocking service
        when(vaccinationsService.getAll()).thenReturn(vaccinationsList);

        // Perform GET request to retrieve all vaccinations
        mockMvc.perform(get("/api/v1/vaccinations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(vaccinationsService, times(1)).getAll();
    }
}
