package com.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
 
import com.model.PetFood;

import com.service.PetFoodService;
 
import jakarta.validation.Valid;
 

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/pet_foods")

@Validated
public class PetFoodController 

{

	@Autowired
	PetFoodService petFoodService;

    @GetMapping
    public ResponseEntity<?> getAllPetFoods() 
    {
        return petFoodService.getAllPetFoods();
    }


    @GetMapping("/{food_id}")
    public ResponseEntity<?> getPetFoodById(@PathVariable("food_id") int foodId) 
    {
        return petFoodService.getPetFoodById(foodId);
    }


    @GetMapping("/search")
    public ResponseEntity<?> getPetFoodByName(@RequestParam("name") String foodName)
    {
       return petFoodService.getPetFoodByName(foodName);
    }


    @GetMapping("/food_type/{type}")
    public ResponseEntity<?> getPetFoodByType(@PathVariable("type") String type) 
    {
        return petFoodService.getPetFoodByType(type);
    }


    @GetMapping("/brand/{brand_name}")
    public ResponseEntity<?> getPetFoodByBrand(@PathVariable("brand_name") String brandName) 
    {
        return petFoodService.getPetFoodByBrand(brandName);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPetFood(@Valid @RequestBody PetFood petFood) 
    {
        return petFoodService.addPetFood(petFood);
    }

    @PutMapping("/update/{food_id}")
    public ResponseEntity<?> updatePetFood(
            @PathVariable("food_id") int foodId,
            @Valid @RequestBody PetFood updatedPetFood) 
    {
    	return petFoodService.updatePetFood(foodId, updatedPetFood);
    }

    @PutMapping("/quantity/{food_id}")
    public ResponseEntity<?> updatePetFoodQuantity(
    	@PathVariable("food_id") int foodId,
            @RequestParam("quantity") int newQuantity)
    {
        return petFoodService.updatePetFoodQuantity(foodId, newQuantity);
    }
}

 