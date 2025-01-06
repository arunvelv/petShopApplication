package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.PetFood;
import com.model.Pets;

class PetFoodTest {

    private PetFood petFood;

    @BeforeEach
    void setUp() {
        List<Pets> petsList = new ArrayList<>();

        // Initialize PetFood
        petFood = new PetFood();
        petFood.setFoodId(1);
        petFood.setName("Premium Dog Food");
        petFood.setBrand("HealthyPaws");
        petFood.setType("Dry");
        petFood.setQuantity(10);
        petFood.setPrice(45.99f);
        petFood.setImageURL("http://example.com/image.jpg");
        petFood.setPets(petsList);
    }

    @Test
    void testPetFoodDefaultConstructor() {
        PetFood defaultPetFood = new PetFood();
        assertNull(defaultPetFood.getName());
        assertNull(defaultPetFood.getBrand());
        assertNull(defaultPetFood.getType());
        assertNull(defaultPetFood.getImageURL());
        assertNull(defaultPetFood.getPets());
    }

    @Test
    void testPetFoodParameterizedConstructor() {
        List<Pets> petsList = new ArrayList<>();
        PetFood petFoodWithParams = new PetFood(2, "Cat Delight", "FelineCare", "Wet", 5, 25.99f,"https://www.pedigree.in/files/styles/webp/public/2024-02/Why-is-packaged-food-mobile.jpg.webp?VersionId=c8Q_uk7ETL0EtUQJMnHPVxo_JMR0Py3n&itok=D0lTt94v", petsList);

        assertEquals(2, petFoodWithParams.getFoodId());
        assertEquals("Cat Delight", petFoodWithParams.getName());
        assertEquals("FelineCare", petFoodWithParams.getBrand());
        assertEquals("Wet", petFoodWithParams.getType());
        assertEquals(5, petFoodWithParams.getQuantity());
        assertEquals(25.99f, petFoodWithParams.getPrice());
        assertEquals("https://www.pedigree.in/files/styles/webp/public/2024-02/Why-is-packaged-food-mobile.jpg.webp?VersionId=c8Q_uk7ETL0EtUQJMnHPVxo_JMR0Py3n&itok=D0lTt94v", petFoodWithParams.getImageURL());
        assertEquals(petsList, petFoodWithParams.getPets());
    }

    @Test
    void testPetFoodGetters() {
        assertEquals(1, petFood.getFoodId());
        assertEquals("Premium Dog Food", petFood.getName());
        assertEquals("HealthyPaws", petFood.getBrand());
        assertEquals("Dry", petFood.getType());
        assertEquals(10, petFood.getQuantity());
        assertEquals(45.99f, petFood.getPrice());
        assertEquals("http://example.com/image.jpg", petFood.getImageURL());
        assertEquals(new ArrayList<>(), petFood.getPets());
    }

    @Test
    void testPetFoodSetters() {
        List<Pets> newPetsList = new ArrayList<>();

        petFood.setFoodId(3);
        petFood.setName("Rabbit Treats");
        petFood.setBrand("BunnyCare");
        petFood.setType("Treat");
        petFood.setQuantity(20);
        petFood.setPrice(30.99f);
        petFood.setImageURL("http://example.com/newimage.jpg");
        petFood.setPets(newPetsList);

        assertEquals(3, petFood.getFoodId());
        assertEquals("Rabbit Treats", petFood.getName());
        assertEquals("BunnyCare", petFood.getBrand());
        assertEquals("Treat", petFood.getType());
        assertEquals(20, petFood.getQuantity());
        assertEquals(30.99f, petFood.getPrice());
        assertEquals("http://example.com/newimage.jpg", petFood.getImageURL());
        assertEquals(newPetsList, petFood.getPets());
    }
}