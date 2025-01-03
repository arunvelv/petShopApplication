package com.serviceTest;

 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.dao.PetCategoriesDAO;
import com.model.PetCategories;
import com.service.PetCategoriesService;
 
class PetCategoriesServiceTest {
 
    @Mock
    private PetCategoriesDAO petCategoriesDAO;
 
    @InjectMocks
    private PetCategoriesService petCategoriesService;
 
    private PetCategories category;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new PetCategories();
        category.setCategoryId(1);
        category.setName("Dogs");
    }
 
    @Test
    void testGetAllCategories() {
        when(petCategoriesDAO.findAll()).thenReturn(Arrays.asList(category));
 
        ResponseEntity<?> response = petCategoriesService.getAllCategories();
 
        assertEquals(200, response.getStatusCodeValue());
        List<PetCategories> categories = (List<PetCategories>) response.getBody();
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Dogs", categories.get(0).getName());
    }
 
    @Test
    void testGetCategoryByIdFound() {
        when(petCategoriesDAO.findById(1)).thenReturn(Optional.of(category));
 
        ResponseEntity<?> response = petCategoriesService.getCategoryById(1);
 
        assertEquals(200, response.getStatusCodeValue());
        PetCategories responseCategory = (PetCategories) response.getBody();
        assertNotNull(responseCategory);
        assertEquals("Dogs", responseCategory.getName());
    }
 
    @Test
    void testGetCategoryByIdNotFound() {
        when(petCategoriesDAO.findById(2)).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petCategoriesService.getCategoryById(2);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Category not found", response.getBody());
    }
 
    @Test
    void testGetCategoryByNameFound() {
        when(petCategoriesDAO.findByName("Dogs")).thenReturn(Optional.of(category));
 
        ResponseEntity<?> response = petCategoriesService.getCategoryByName("Dogs");
 
        assertEquals(200, response.getStatusCodeValue());
        PetCategories responseCategory = (PetCategories) response.getBody();
        assertNotNull(responseCategory);
        assertEquals("Dogs", responseCategory.getName());
    }
 
    @Test
    void testGetCategoryByNameNotFound() {
        when(petCategoriesDAO.findByName("Cats")).thenReturn(Optional.empty());
 
        ResponseEntity<?> response = petCategoriesService.getCategoryByName("Cats");
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Category not found", response.getBody());
    }
 
    @Test
    void testAddCategorySuccess() {
        when(petCategoriesDAO.findByName("Dogs")).thenReturn(Optional.empty());
        when(petCategoriesDAO.save(category)).thenReturn(category);
 
        ResponseEntity<?> response = petCategoriesService.addCategory(category);
 
        assertEquals(201, response.getStatusCodeValue());
        PetCategories responseCategory = (PetCategories) response.getBody();
        assertNotNull(responseCategory);
        assertEquals("Dogs", responseCategory.getName());
    }
 
    @Test
    void testAddCategoryConflict() {
        when(petCategoriesDAO.findByName("Dogs")).thenReturn(Optional.of(category));
 
        ResponseEntity<?> response = petCategoriesService.addCategory(category);
 
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Category with this name already exists", response.getBody());
    }
 
    @Test
    void testUpdateCategorySuccess() {
        when(petCategoriesDAO.findById(1)).thenReturn(Optional.of(category));
        when(petCategoriesDAO.save(category)).thenReturn(category);
 
        PetCategories updatedCategory = new PetCategories();
        updatedCategory.setName("Updated Dogs");
 
        ResponseEntity<?> response = petCategoriesService.updateCategory(1, updatedCategory);
 
        assertEquals(200, response.getStatusCodeValue());
        PetCategories responseCategory = (PetCategories) response.getBody();
        assertNotNull(responseCategory);
        assertEquals("Updated Dogs", responseCategory.getName());
    }
 
    @Test
    void testUpdateCategoryNotFound() {
        when(petCategoriesDAO.findById(2)).thenReturn(Optional.empty());
 
        PetCategories updatedCategory = new PetCategories();
        updatedCategory.setName("Updated Dogs");
 
        ResponseEntity<?> response = petCategoriesService.updateCategory(2, updatedCategory);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Category not found", response.getBody());
    }
}