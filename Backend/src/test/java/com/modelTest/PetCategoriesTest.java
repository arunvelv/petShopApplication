package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.PetCategories;
import com.model.Pets;

class PetCategoriesTest {

    private PetCategories petCategory;

    @BeforeEach
    void setUp() {
        List<Pets> petsList = new ArrayList<>();
        
        // Initialize PetCategories
        petCategory = new PetCategories();
        petCategory.setCategoryId(1);
        petCategory.setName("Dogs");
        petCategory.setPetsList(petsList);
    }

    @Test
    void testPetCategoriesDefaultConstructor() {
        PetCategories defaultCategory = new PetCategories();
        
        assertEquals(0, defaultCategory.getCategoryId());
        assertNull(defaultCategory.getName());
        assertNull(defaultCategory.getPetsList());
    }

    @Test
    void testPetCategoriesParameterizedConstructor() {
        List<Pets> petsList = new ArrayList<>();
        PetCategories categoryWithParams = new PetCategories(2, "Cats", petsList);

        assertEquals(2, categoryWithParams.getCategoryId());
        assertEquals("Cats", categoryWithParams.getName());
        assertEquals(petsList, categoryWithParams.getPetsList());
    }

    @Test
    void testPetCategoriesGetters() {
        assertEquals(1, petCategory.getCategoryId());
        assertEquals("Dogs", petCategory.getName());
        assertEquals(new ArrayList<>(), petCategory.getPetsList());
    }

    @Test
    void testPetCategoriesSetters() {
        List<Pets> newPetsList = new ArrayList<>();
        
        petCategory.setCategoryId(3);
        petCategory.setName("Birds");
        petCategory.setPetsList(newPetsList);

        assertEquals(3, petCategory.getCategoryId());
        assertEquals("Birds", petCategory.getName());
        assertEquals(newPetsList, petCategory.getPetsList());
    }
}