package com.serviceTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.dao.PetFoodDAO;
import com.model.PetFood;
import com.service.PetFoodService;
 
class PetFoodServiceTest {
 
    @Mock
    private PetFoodDAO petFoodDAO;
 
    @InjectMocks
    private PetFoodService petFoodService;
 
    private PetFood petFood;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        petFood = new PetFood();
        petFood.setFoodId(1);
        petFood.setName("Premium Dog Food");
        petFood.setBrand("BrandA");
        petFood.setType("Dry");
        petFood.setQuantity(50);
        petFood.setPrice(300.0f);
    }
 
    @Test
    void testGetAllPetFoods() {
        when(petFoodDAO.findAll()).thenReturn(Arrays.asList(petFood));
 
        ResponseEntity<?> response = petFoodService.getAllPetFoods();
 
        assertEquals(200, response.getStatusCodeValue());
        List<PetFood> petFoods = (List<PetFood>) response.getBody();
        assertNotNull(petFoods);
        assertEquals(1, petFoods.size());
        assertEquals("Premium Dog Food", petFoods.get(0).getName());
    }
 
    @Test
    void testGetPetFoodByIdFound() {
        when(petFoodDAO.findById(1)).thenReturn(Optional.of(petFood));
 
        ResponseEntity<?> response = petFoodService.getPetFoodById(1);
 
        assertEquals(200, response.getStatusCodeValue());
        PetFood responsePetFood = (PetFood) response.getBody();
        assertNotNull(responsePetFood);
        assertEquals("Premium Dog Food", responsePetFood.getName());
    }
 
    @Test
    void testGetPetFoodByIdNotFound() {
        when(petFoodDAO.findById(2)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petFoodService.getPetFoodById(2);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Pet food not found", response.getBody());
    }
    
    @Test
    void testGetPetFoodByNameFound() {
        // Arrange
        PetFood petFood = new PetFood();
        petFood.setName("Premium Dog Food");
 
        when(petFoodDAO.findByName("Premium Dog Food")).thenReturn(List.of(petFood));
 
        // Act
        ResponseEntity<?> response = petFoodService.getPetFoodByName("Premium Dog Food");
 
        // Assert
        assertEquals(200, response.getStatusCodeValue());
 
        // Ensure the response body is a List and extract its value
        assertTrue(response.getBody() instanceof List);
        List<PetFood> petFoods = (List<PetFood>) response.getBody();
 
        // Validate the extracted PetFood object
        assertNotNull(petFoods);
        assertEquals(1, petFoods.size());
        assertEquals("Premium Dog Food", petFoods.get(0).getName());
    }
 
 
    @Test
    void testGetPetFoodByNameNotFound() {
        when(petFoodDAO.findByName("Unknown Food")).thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = petFoodService.getPetFoodByName("Unknown Food");
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("No pet food found with name: Unknown Food", response.getBody());
    }
 
 
    @Test
    void testAddPetFoodSuccess() {
        when(petFoodDAO.save(petFood)).thenReturn(petFood);
 
        ResponseEntity<?> response = petFoodService.addPetFood(petFood);
 
        assertEquals(201, response.getStatusCodeValue());
        PetFood responsePetFood = (PetFood) response.getBody();
        assertNotNull(responsePetFood);
        assertEquals("Premium Dog Food", responsePetFood.getName());
    }
 
    @Test
    void testAddPetFoodFailure() {
        PetFood invalidPetFood = new PetFood();
        ResponseEntity<?> response = petFoodService.addPetFood(invalidPetFood);
 
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Pet food name cannot be empty", response.getBody());
    }
 
    @Test
    void testUpdatePetFoodSuccess() {
        when(petFoodDAO.findById(1)).thenReturn(Optional.of(petFood));
        when(petFoodDAO.save(petFood)).thenReturn(petFood);
 
        PetFood updatedPetFood = new PetFood();
        updatedPetFood.setName("Updated Dog Food");
        updatedPetFood.setBrand("Updated Brand");
        updatedPetFood.setType("Wet");
        updatedPetFood.setQuantity(30);
        updatedPetFood.setPrice(400.0f);
 
        ResponseEntity<?> response = petFoodService.updatePetFood(1, updatedPetFood);
 
        assertEquals(200, response.getStatusCodeValue());
        PetFood responsePetFood = (PetFood) response.getBody();
        assertNotNull(responsePetFood);
        assertEquals("Updated Dog Food", responsePetFood.getName());
    }
 
    @Test
    void testUpdatePetFoodNotFound() {
        when(petFoodDAO.findById(2)).thenReturn(Optional.empty());
 
        PetFood updatedPetFood = new PetFood();
        ResponseEntity<?> response = petFoodService.updatePetFood(2, updatedPetFood);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Pet food not found", response.getBody());
    }
 
    @Test
    void testUpdatePetFoodQuantitySuccess() {
        when(petFoodDAO.findById(1)).thenReturn(Optional.of(petFood));
 
        ResponseEntity<?> response = petFoodService.updatePetFoodQuantity(1, 60);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pet food quantity updated successfully", response.getBody());
    }
 
    @Test
    void testUpdatePetFoodQuantityNotFound() {
        when(petFoodDAO.findById(2)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petFoodService.updatePetFoodQuantity(2, 60);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Pet food not found", response.getBody());
    }
}