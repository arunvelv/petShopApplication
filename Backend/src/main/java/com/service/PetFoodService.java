package com.service;
 
import java.util.List;

import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
 
 
import com.dao.PetFoodDAO;

import com.model.PetFood;
 
 
@Service

public class PetFoodService

{

	 @Autowired

	 private PetFoodDAO petFoodDAO;

	 public ResponseEntity<?> getAllPetFoods() 

	 {

	     List<PetFood> petFoods = petFoodDAO.findAll();

	     return new ResponseEntity<>(petFoods, HttpStatus.OK);

	 }

	 public ResponseEntity<?> getPetFoodById(int id) 

	 {

	        Optional<PetFood> petFood = petFoodDAO.findById(id);

	        if (petFood.isPresent())

	        {

	            return new ResponseEntity<>(petFood.get(), HttpStatus.OK);

	        } 

	        else

	        {

	            return new ResponseEntity<>("Pet food not found", HttpStatus.NOT_FOUND);

	        }

	  }

	 public ResponseEntity<?> getPetFoodByName(String name) 

	 {

	   List<PetFood> petFoods = petFoodDAO.findByName(name);

	   if (!petFoods.isEmpty()) 

	   { 

		   return new ResponseEntity<>(petFoods, HttpStatus.OK);

	   } else 

	   {

		   return new ResponseEntity<>("No pet food found with name: " + name, HttpStatus.NOT_FOUND);

	   }

	 }

	 public ResponseEntity<?> getPetFoodByType(String type) {

	        List<PetFood> petFoods = petFoodDAO.findByType(type);

	        if (!petFoods.isEmpty()) {

	            return new ResponseEntity<>(petFoods, HttpStatus.OK);

	        } else {

	            return new ResponseEntity<>("No pet food found with type: " + type, HttpStatus.NOT_FOUND);

	        }

	    }

	 public ResponseEntity<?> getPetFoodByBrand(String brand) {

	        List<PetFood> petFoods = petFoodDAO.findByBrand(brand);

	        if (!petFoods.isEmpty()) {

	            return new ResponseEntity<>(petFoods, HttpStatus.OK);

	        } else {

	            return new ResponseEntity<>("No pet food found with brand: " + brand, HttpStatus.NOT_FOUND);

	        }

	    }

	 public ResponseEntity<?> addPetFood(PetFood petFood) {

	        try {

	            if (petFood == null || petFood.getName() == null || petFood.getName().isEmpty()) {

	                return new ResponseEntity<>("Pet food name cannot be empty", HttpStatus.BAD_REQUEST);

	            }

	            PetFood savedPetFood = petFoodDAO.save(petFood);

	            return new ResponseEntity<>(savedPetFood, HttpStatus.CREATED);

	        } catch (Exception e) {

	            return new ResponseEntity<>("Failed to add pet food: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	        }

	    }

	 public ResponseEntity<?> updatePetFood(int foodId, PetFood updatedPetFood) 

	 {

	        Optional<PetFood> existingPetFoodOptional = petFoodDAO.findById(foodId);
 
	        if (existingPetFoodOptional.isPresent()) 

	        {

	            try 

	            {

	                if (updatedPetFood == null) 

	                {

	                    return new ResponseEntity<>("Updated pet food cannot be null", HttpStatus.BAD_REQUEST);

	                }

	                PetFood existingPetFood = existingPetFoodOptional.get();
 
	                // Update all fields

	                existingPetFood.setName(updatedPetFood.getName());

	                existingPetFood.setBrand(updatedPetFood.getBrand());

	                existingPetFood.setType(updatedPetFood.getType());

	                existingPetFood.setQuantity(updatedPetFood.getQuantity());

	                existingPetFood.setPrice(updatedPetFood.getPrice());
	     
	                existingPetFood.setImageURL(updatedPetFood.getImageURL());
	                PetFood savedPetFood = petFoodDAO.save(existingPetFood);

	                return new ResponseEntity<>(savedPetFood, HttpStatus.OK);

	            } 

	            catch (Exception e) 

	            {

	                return new ResponseEntity<>("Failed to update pet food: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	            }

	        } 

	        else 

	        {

	            return new ResponseEntity<>("Pet food not found", HttpStatus.NOT_FOUND);

	        }

	    }

	 public ResponseEntity<?> updatePetFoodQuantity(int foodId, int newQuantity) 

	 {

	        Optional<PetFood> existingPetFoodOptional = petFoodDAO.findById(foodId);
 
	        if (existingPetFoodOptional.isPresent()) 

	        {

	            try 

	            {

	                petFoodDAO.updateQuantityByFoodId(newQuantity, foodId);

	                return new ResponseEntity<>("Pet food quantity updated successfully", HttpStatus.OK);

	            } 

	            catch (Exception e) 

	            {

	                return new ResponseEntity<>("Failed to update pet food quantity: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	            }

	        }

	        else 

	        {

	            return new ResponseEntity<>("Pet food not found", HttpStatus.NOT_FOUND);

	        }

	    }

 
}

 