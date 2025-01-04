package com.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
 
import com.model.PetCategories;

import com.service.PetCategoriesService;
 
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")

public class PetCategoriesController 

{

	@Autowired

    private PetCategoriesService petCategoriesService;

	@GetMapping

    public ResponseEntity<?> getAllCategories()

	{

        return petCategoriesService.getAllCategories();

    }
 
	@GetMapping("/{category_id}")

    public ResponseEntity<?> getCategoryById(@PathVariable("category_id") int categoryId) 

	{

        return petCategoriesService.getCategoryById(categoryId);

    }

	@GetMapping("/name/{category_name}")

    public ResponseEntity<?> getCategoryByName(@PathVariable("category_name") String categoryName) 

	{

        return petCategoriesService.getCategoryByName(categoryName);

    }

	@PostMapping("/add")

    public ResponseEntity<?> addCategory(@RequestBody PetCategories category) 

	{

        return petCategoriesService.addCategory(category);

    }

	 @PutMapping("/update/{category_id}")

	    public ResponseEntity<?> updateCategory(@PathVariable("category_id") int categoryId, @RequestBody PetCategories updatedCategory)

	 {

	        return petCategoriesService.updateCategory(categoryId, updatedCategory);

	  }


}

 