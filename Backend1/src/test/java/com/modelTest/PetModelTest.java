package com.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Employee;
import com.model.GroomingService;
import com.model.PetCategories;
import com.model.PetFood;
import com.model.Pets;
import com.model.Suppliers;
import com.model.Transactions;
import com.model.Vaccinations;

class PetModelTest {

    private Pets pet;
    
    @BeforeEach
    void setUp() {
        List<Transactions> transactionsList = new ArrayList<>();
        
        PetCategories category = new PetCategories();
        category.setCategoryId(1);
        category.setName("Dog");

        GroomingService groomingService = new GroomingService();
        groomingService.setServiceId(1);
        groomingService.setName("Full Groom");
        groomingService.setPrice(50.0f);

        Suppliers supplier = new Suppliers();
        supplier.setSuppliersId(1);
        supplier.setName("Pet Supplies Co.");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("John Doe");

        PetFood petFood = new PetFood();
        petFood.setFoodId(1);
        petFood.setName("Dog Food");
        petFood.setPrice(30.0f);

        Vaccinations vaccination = new Vaccinations();
        vaccination.setVaccinationId(1);
        vaccination.setName("Rabies");

        pet = new Pets();
        pet.setPetId(1);
        pet.setName("Max");
        pet.setBreed("Golden Retriever");
        pet.setAge(5);
        pet.setPrice(200.0f);
        pet.setDescription("Friendly dog");
        pet.setImageUrl("image_url_here");
        pet.setCategory(category);
        pet.setTransactions(transactionsList);
        pet.setGrooming_services(groomingService);
        pet.setSuppliers(supplier);
        pet.setEmployees(employee);
        pet.setPet_food(petFood);
        pet.setVaccinations(vaccination);
    }

    @Test
    void testPetsDefaultConstructor() {
        Pets defaultPet = new Pets();

        assertEquals(0, defaultPet.getPetId());
        assertNull(defaultPet.getName());
        assertNull(defaultPet.getBreed());
        assertEquals(0, defaultPet.getAge());
        assertEquals(0.0f, defaultPet.getPrice());
        assertNull(defaultPet.getDescription());
        assertNull(defaultPet.getImageUrl());
        assertNull(defaultPet.getCategory());
        assertNull(defaultPet.getTransactions());
        assertNull(defaultPet.getGrooming_services());
        assertNull(defaultPet.getSuppliers());
        assertNull(defaultPet.getEmployees());
        assertNull(defaultPet.getPet_food());
        assertNull(defaultPet.getVaccinations());
    }

    @Test
    void testPetsGetters() {
        assertEquals(1, pet.getPetId());
        assertEquals("Max", pet.getName());
        assertEquals("Golden Retriever", pet.getBreed());
        assertEquals(5, pet.getAge());
        assertEquals(200.0f, pet.getPrice());
        assertEquals("Friendly dog", pet.getDescription());
        assertEquals("image_url_here", pet.getImageUrl());
        assertEquals("Dog", pet.getCategory().getName());
        assertEquals(1, pet.getCategory().getCategoryId());
        assertEquals(0, pet.getTransactions().size()); // Empty list
        assertEquals("Full Groom", pet.getGrooming_services().getName());
        assertEquals(1, pet.getGrooming_services().getServiceId());
        assertEquals("Pet Supplies Co.", pet.getSuppliers().getName());
        assertEquals(1, pet.getSuppliers().getSuppliersId());
        assertEquals("John Doe", pet.getEmployees().getName());
        assertEquals(1, pet.getEmployees().getEmployeeId());
        assertEquals("Dog Food", pet.getPet_food().getName());
        assertEquals(1, pet.getPet_food().getFoodId());
        assertEquals("Rabies", pet.getVaccinations().getName());
        assertEquals(1, pet.getVaccinations().getVaccinationId());
    }

    @Test
    void testPetsSetters() {
        PetCategories newCategory = new PetCategories();
        newCategory.setCategoryId(2);
        newCategory.setName("Cat");

        GroomingService newGroomingService = new GroomingService();
        newGroomingService.setServiceId(2);
        newGroomingService.setName("Nail Trim");
        newGroomingService.setPrice(25.0f);

        Suppliers newSupplier = new Suppliers();
        newSupplier.setSuppliersId(2);
        newSupplier.setName("Cat Supplies Co.");

        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(2);
        newEmployee.setName("Jane Smith");

        PetFood newPetFood = new PetFood();
        newPetFood.setFoodId(2);
        newPetFood.setName("Cat Food");
        newPetFood.setPrice(20.0f);

        Vaccinations newVaccination = new Vaccinations();
        newVaccination.setVaccinationId(2);
        newVaccination.setName("Feline Distemper");

        pet.setPetId(2);
        pet.setName("Bella");
        pet.setBreed("Persian Cat");
        pet.setAge(3);
        pet.setPrice(150.0f);
        pet.setDescription("Cute cat");
        pet.setImageUrl("new_image_url");
        pet.setCategory(newCategory);
        pet.setTransactions(new ArrayList<>());
        pet.setGrooming_services(newGroomingService);
        pet.setSuppliers(newSupplier);
        pet.setEmployees(newEmployee);
        pet.setPet_food(newPetFood);
        pet.setVaccinations(newVaccination);

        assertEquals(2, pet.getPetId());
        assertEquals("Bella", pet.getName());
        assertEquals("Persian Cat", pet.getBreed());
        assertEquals(3, pet.getAge());
        assertEquals(150.0f, pet.getPrice());
        assertEquals("Cute cat", pet.getDescription());
        assertEquals("new_image_url", pet.getImageUrl());
        assertEquals("Cat", pet.getCategory().getName());
        assertEquals(2, pet.getCategory().getCategoryId());
        assertEquals(0, pet.getTransactions().size()); // Empty list
        assertEquals("Nail Trim", pet.getGrooming_services().getName());
        assertEquals(2, pet.getGrooming_services().getServiceId());
        assertEquals("Cat Supplies Co.", pet.getSuppliers().getName());
        assertEquals(2, pet.getSuppliers().getSuppliersId());
        assertEquals("Jane Smith", pet.getEmployees().getName());
        assertEquals(2, pet.getEmployees().getEmployeeId());
        assertEquals("Cat Food", pet.getPet_food().getName());
        assertEquals(2, pet.getPet_food().getFoodId());
        assertEquals("Feline Distemper", pet.getVaccinations().getName());
        assertEquals(2, pet.getVaccinations().getVaccinationId());
    }
}
