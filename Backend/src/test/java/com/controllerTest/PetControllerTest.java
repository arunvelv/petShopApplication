package com.controllerTest;

import com.model.*;
import com.model.Pets;
import com.service.PetsService;
import com.controller.PetsController;
import com.dao.PetCategoriesDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    @InjectMocks
    private PetsController petsController;

    @Mock
    private PetsService petsService;

    @Mock
    private PetCategoriesDAO petCategoriesDAO;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(petsController).build();  // Setup MockMvc
    }

    @Test
    void testGetAllPets() throws Exception {
        Pets pet1 = new Pets(0, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", new PetCategories(), null, null, null, null, null, null);
        Pets pet2 = new Pets(0, "Cat", "Persian", 3, 800, "Cute cat", "image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(Arrays.asList(pet1, pet2)))
                .when(petsService).getAllPets();

        mockMvc.perform(get("/api/v1/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dog"))
                .andExpect(jsonPath("$[1].name").value("Cat"));

        verify(petsService, times(1)).getAllPets();
    }

    @Test
    void testGetPetById() throws Exception {
        int petId = 1;
        Pets pet = new Pets(petId, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(pet))
                .when(petsService).getPetById(petId);

        mockMvc.perform(get("/api/v1/pets/{pet_id}", petId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dog"));

        verify(petsService, times(1)).getPetById(petId);
    }

    @Test
    void testGetPetsByCategory() throws Exception {
        String category = "Dog";
        Pets pet = new Pets(0, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(Arrays.asList(pet)))
                .when(petsService).getPetsByCategory(category);

        mockMvc.perform(get("/api/v1/pets/category/{category}", category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dog"));

        verify(petsService, times(1)).getPetsByCategory(category);
    }

    @Test
    void testAddPet() throws Exception {
        PetCategories category = new PetCategories();
        category.setCategoryId(1);
        Pets pet = new Pets(0, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", category, null, null, null, null, null, null);

        when(petCategoriesDAO.findById(category.getCategoryId()))
                .thenReturn(Optional.of(category));

        doAnswer(invocation -> ResponseEntity.status(201).body(pet))
                .when(petsService).addPet(any(Pets.class));

        mockMvc.perform(post("/api/v1/pets/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dog"));

        verify(petCategoriesDAO, times(1)).findById(category.getCategoryId());
        verify(petsService, times(1)).addPet(any(Pets.class));
    }

    @Test
    void testUpdatePet() throws Exception {
        int petId = 1;
        Pets updatedPet = new Pets(petId, "Updated Dog", "Bulldog", 6, 1200, "Updated description", "updated_image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(updatedPet))
                .when(petsService).updatePet(eq(petId), any(Pets.class));

        mockMvc.perform(put("/api/v1/pets/update/{pet_id}", petId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedPet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Dog"));

        verify(petsService, times(1)).updatePet(eq(petId), any(Pets.class));
    }

    @Test
    void testGetPetWithGroomingServices() throws Exception {
        int petId = 1;
        Pets pet = new Pets(petId, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(pet))
                .when(petsService).getPetWithGroomingServices(petId);

        mockMvc.perform(get("/api/v1/pets/grooming_services/{pet_id}", petId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dog"));

        verify(petsService, times(1)).getPetWithGroomingServices(petId);
    }

    @Test
    void testGetPetWithVaccinations() throws Exception {
        int petId = 1;
        Pets pet = new Pets(petId, "Dog", "Bulldog", 5, 1000, "Friendly dog", "image_url", new PetCategories(), null, null, null, null, null, null);

        doAnswer(invocation -> ResponseEntity.ok(pet))
                .when(petsService).getPetWithVaccinations(petId);

        mockMvc.perform(get("/api/v1/pets/vaccinations/{pet_id}", petId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dog"));

        verify(petsService, times(1)).getPetWithVaccinations(petId);
    }
}