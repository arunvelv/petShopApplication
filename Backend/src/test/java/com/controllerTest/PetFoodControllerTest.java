package com.controllerTest;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.controller.PetFoodController;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.model.PetFood;

import com.service.PetFoodService;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Arrays;
 
public class PetFoodControllerTest {
 
    @InjectMocks

    private PetFoodController petFoodController;
 
    @Mock

    private PetFoodService petFoodService;
 
    @Autowired

    private MockMvc mockMvc;
 
    private ObjectMapper objectMapper = new ObjectMapper();
 
    @BeforeEach

    public void setUp() {

        MockitoAnnotations.openMocks(this);  // Initialize mocks

        mockMvc = MockMvcBuilders.standaloneSetup(petFoodController).build();  // Setup MockMvc

    }
 
    @Test

    public void testGetAllPetFoods() throws Exception {

        PetFood petFood1 = new PetFood();

        petFood1.setFoodId(1);

        petFood1.setName("Dog Food");

        petFood1.setPrice(10.5f);
 
        PetFood petFood2 = new PetFood();

        petFood2.setFoodId(2);

        petFood2.setName("Cat Food");

        petFood2.setPrice(8.0f);
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.ok(Arrays.asList(petFood1, petFood2));

        }).when(petFoodService).getAllPetFoods();
 
        // Perform the GET request and validate the response

        mockMvc.perform(get("/api/v1/pet_foods"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].foodId").value(1))

                .andExpect(jsonPath("$[0].name").value("Dog Food"))

                .andExpect(jsonPath("$[1].foodId").value(2))

                .andExpect(jsonPath("$[1].name").value("Cat Food"));

    }
 
    @Test

    public void testGetPetFoodById() throws Exception {

        int foodId = 1;

        PetFood petFood = new PetFood();

        petFood.setFoodId(foodId);

        petFood.setName("Dog Food");

        petFood.setPrice(10.5f);
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.ok(petFood);

        }).when(petFoodService).getPetFoodById(foodId);
 
        // Perform the GET request and validate the response

        mockMvc.perform(get("/api/v1/pet_foods/{food_id}", foodId))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.foodId").value(foodId))

                .andExpect(jsonPath("$.name").value("Dog Food"));

    }
 
    @Test

    public void testGetPetFoodByName() throws Exception {

        String foodName = "Dog Food";

        PetFood petFood = new PetFood();

        petFood.setName(foodName);
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.ok(Arrays.asList(petFood));

        }).when(petFoodService).getPetFoodByName(foodName);
 
        // Perform the GET request and validate the response

        mockMvc.perform(get("/api/v1/pet_foods/search?name={food_name}", foodName))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name").value(foodName));

    }
 
    @Test

    public void testAddPetFood() throws Exception {

        PetFood petFood = new PetFood();

        petFood.setName("New Pet Food");

        petFood.setPrice(12.0f);
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.status(201).body(petFood);  // Simulate created response

        }).when(petFoodService).addPetFood(any(PetFood.class));
 
        // Perform the POST request and validate the response

        mockMvc.perform(post("/api/v1/pet_foods/add")

                        .contentType("application/json")

                        .content(objectMapper.writeValueAsString(petFood)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.name").value("New Pet Food"));

    }
 
    @Test

    public void testUpdatePetFood() throws Exception {

        int foodId = 1;

        PetFood updatedPetFood = new PetFood();

        updatedPetFood.setName("Updated Pet Food");

        updatedPetFood.setPrice(15.0f);
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.ok(updatedPetFood);

        }).when(petFoodService).updatePetFood(eq(foodId), any(PetFood.class));
 
        // Perform the PUT request and validate the response

        mockMvc.perform(put("/api/v1/pet_foods/update/{food_id}", foodId)

                        .contentType("application/json")

                        .content(objectMapper.writeValueAsString(updatedPetFood)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Updated Pet Food"));

    }
 
    @Test

    public void testUpdatePetFoodQuantity() throws Exception {

        int foodId = 1;

        int newQuantity = 100;
 
        // Use doAnswer to mock the service method

        doAnswer(invocation -> {

            return ResponseEntity.ok("Pet food quantity updated");

        }).when(petFoodService).updatePetFoodQuantity(foodId, newQuantity);
 
        // Perform the PUT request and validate the response

        mockMvc.perform(put("/api/v1/pet_foods/quantity/{food_id}", foodId)

                        .param("quantity", String.valueOf(newQuantity)))

                .andExpect(status().isOk())

                .andExpect(content().string("Pet food quantity updated"));

    }

}

 