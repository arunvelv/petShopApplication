package com.controllerTest;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.controller.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.PetCategories;
import com.service.PetCategoriesService;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Arrays;
 
public class PetCategoriesControllerTest {
 
    @InjectMocks
    private PetCategoriesController petCategoriesController;
 
    @Mock
    private PetCategoriesService petCategoriesService;
 
    @Autowired
    private MockMvc mockMvc;
 
    private ObjectMapper objectMapper = new ObjectMapper();
 
    public PetCategoriesControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petCategoriesController).build();
    }
 
    @Test
    public void testGetAllCategories() throws Exception {
        PetCategories category1 = new PetCategories();
        category1.setCategoryId(1);
        category1.setName("Dogs");
        PetCategories category2 = new PetCategories();
        category2.setCategoryId(2);
        category2.setName("Cats");
 
        // Create a response entity wrapping the list of PetCategories
        ResponseEntity<?> responseEntity = ResponseEntity.ok(Arrays.asList(category1, category2));
 
        // Mock the service call
        doReturn(responseEntity).when(petCategoriesService).getAllCategories();
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].name").value("Dogs"))
                .andExpect(jsonPath("$[1].categoryId").value(2))
                .andExpect(jsonPath("$[1].name").value("Cats"));
    }
 
    @Test
    public void testGetCategoryById() throws Exception {
        int categoryId = 1;
        PetCategories category = new PetCategories();
        category.setCategoryId(categoryId);
        category.setName("Test Category");
 
        // Create a response entity wrapping the PetCategory object
        ResponseEntity<?> responseEntity = ResponseEntity.ok(category);
 
        // Mock the service call
        doReturn(responseEntity).when(petCategoriesService).getCategoryById(categoryId);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/v1/categories/{category_id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(categoryId))
                .andExpect(jsonPath("$.name").value("Test Category"));
    }
 
    @Test
    public void testGetCategoryByName() throws Exception {
        String categoryName = "Test Category";
        PetCategories category = new PetCategories();
        category.setName(categoryName);
 
        // Create a response entity wrapping the PetCategory object
        ResponseEntity<?> responseEntity = ResponseEntity.ok(category);
 
        // Mock the service call
        doReturn(responseEntity).when(petCategoriesService).getCategoryByName(categoryName);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/v1/categories/name/{category_name}", categoryName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(categoryName));
    }
 
    @Test
    public void testAddCategory() throws Exception {
        PetCategories category = new PetCategories();
        category.setName("New Category");
 
        // Create a response entity wrapping the success message or PetCategory
        ResponseEntity<?> responseEntity = ResponseEntity.ok(category);
 
        // Mock the service call
        doReturn(responseEntity).when(petCategoriesService).addCategory(any(PetCategories.class));
 
        // Perform the POST request and validate the response
        mockMvc.perform(post("/api/v1/categories/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Category"));
    }
 
    @Test
    public void testUpdateCategory() throws Exception {
        int categoryId = 1;
        PetCategories updatedCategory = new PetCategories();
        updatedCategory.setName("Updated Category");
 
        // Create a response entity wrapping the updated PetCategory object
        ResponseEntity<?> responseEntity = ResponseEntity.ok(updatedCategory);
 
        // Mock the service call
        doReturn(responseEntity).when(petCategoriesService).updateCategory(eq(categoryId), any(PetCategories.class));
 
        // Perform the PUT request and validate the response
        mockMvc.perform(put("/api/v1/categories/update/{category_id}", categoryId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }
}