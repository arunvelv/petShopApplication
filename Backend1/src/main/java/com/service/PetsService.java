package com.service;
import com.dao.PetsDAO;


import com.model.Pets;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
 
import java.util.List;

import java.util.Optional;
 
@Service

public class PetsService {
 
    @Autowired

    private PetsDAO petsDAO;
 
    public ResponseEntity<?> getAllPets() {

        List<Pets> petList = petsDAO.findAll();

        return new ResponseEntity<>(petList, HttpStatus.OK);

    }
 
    public ResponseEntity<?> getPetById(int petId) {

        Optional<Pets> pet = petsDAO.findById(petId);

        if (pet.isPresent()) {

            return new ResponseEntity<>(pet.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetsByCategory(String category) {

        List<Pets> petList = petsDAO.findByCategoryName(category);

        return new ResponseEntity<>(petList, HttpStatus.OK);

    }
 
    public ResponseEntity<?> getPetWithGroomingServices(int petId) {

        Pets pet = petsDAO.findPetsWithGroomingServices(petId);

        if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Grooming Services not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetWithVaccinations(int petId) {

        Pets pet = petsDAO.findPetsWithVaccinations(petId);

        if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Vaccinations not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetWithFoodInfo(int petId) {

        Pets pet = petsDAO.findPetsWithFoodInfo(petId);

         if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Food Info not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetWithSuppliers(int petId) {

        Pets pet = petsDAO.findPetsWithSuppliers(petId);

         if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Suppliers not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetWithTransactionHistory(int petId) {

        Pets pet = petsDAO.findPetsWithTransactionHistory(petId);

         if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Transaction History not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> getPetWithEmployees(int petId) {

        Optional<Pets> pet = petsDAO.findPetWithEmployees(petId);

         if (pet != null) {

            return new ResponseEntity<>(pet, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Pet or Employees not found", HttpStatus.NOT_FOUND);

        }

    }
 
    public ResponseEntity<?> addPet(Pets pet) 

    {

        Pets savedPet = petsDAO.save(pet);

        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);

    }
 
    public ResponseEntity<?> updatePet(int petId, Pets updatedPet) {

        Optional<Pets> existingPet = petsDAO.findById(petId);

        if (existingPet.isPresent()) {

            Pets pet = existingPet.get();

            pet.setName(updatedPet.getName());

            pet.setAge(updatedPet.getAge());

            pet.setBreed(updatedPet.getBreed());

            pet.setCategory(updatedPet.getCategory());
            pet.setDescription(updatedPet.getDescription());

            pet.setImageUrl(updatedPet.getImageUrl());

            pet.setPrice(updatedPet.getPrice());


            Pets savedPet = petsDAO.save(pet);

            return new ResponseEntity<>(savedPet, HttpStatus.OK);

        } 

        else 

        {

            return new ResponseEntity<>("Pet not found", HttpStatus.NOT_FOUND);

        }

    }
 
    

}
 