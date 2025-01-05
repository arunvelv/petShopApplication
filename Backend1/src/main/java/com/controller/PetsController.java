package com.controller;
 
 
import com.dao.PetCategoriesDAO;
import com.model.PetCategories;
import com.model.Pets;
import com.service.PetsService;
 
import jakarta.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/pets")
public class PetsController {
 
    @Autowired
    private PetsService petsService;
    
    @Autowired
    private PetCategoriesDAO petCategory;
 
    @GetMapping
    public ResponseEntity<?> getAllPets() {
        return petsService.getAllPets();
    }
 
    @GetMapping("/{pet_id}")
    public ResponseEntity<?> getPetById(@PathVariable("pet_id") int petId) {
        return petsService.getPetById(petId);
    }
 
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getPetsByCategory(@PathVariable("category") String category) {
        return petsService.getPetsByCategory(category);
    }
 
    @GetMapping("/grooming_services/{pet_id}")
    public ResponseEntity<?> getPetWithGroomingServices(@PathVariable("pet_id") int petId) {
        return petsService.getPetWithGroomingServices(petId);
    }
 
    @GetMapping("/vaccinations/{pet_id}")
    public ResponseEntity<?> getPetWithVaccinations(@PathVariable("pet_id") int petId) {
        return petsService.getPetWithVaccinations(petId);
    }
 
    @GetMapping("/food_info/{pet_id}")
    public ResponseEntity<?> getPetWithFoodInfo(@PathVariable("pet_id") int petId) {
        return petsService.getPetWithFoodInfo(petId);
    }
 
    @GetMapping("/suppliers/{pet_id}")
    public ResponseEntity<?> getPetWithSuppliers(@PathVariable("pet_id") int petId) {
        return petsService.getPetWithSuppliers(petId);
    }
 
    @GetMapping("/transaction_history/{pet_id}")
    public ResponseEntity<?> getPetWithTransactionHistory(@PathVariable("pet_id") int petId) {
        return petsService.getPetWithTransactionHistory(petId);
    }
 
    @PostMapping("/add")
    public ResponseEntity<?> addPet(@Valid @RequestBody Pets pet) {
        PetCategories category = pet.getCategory();
        PetCategories completeCategory = petCategory.findById(category.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + category.getCategoryId()));
        pet.setCategory(completeCategory);
        return petsService.addPet(pet);
    }

 
    // 10. Update an existing pet
    @PutMapping("/update/{pet_id}")
    public ResponseEntity<?> updatePet(@PathVariable("pet_id") int petId, @RequestBody Pets updatedPet) {
        return petsService.updatePet(petId, updatedPet);
    }
}
 
 