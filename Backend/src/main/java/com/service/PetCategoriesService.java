package com.service;
 
import java.util.List;

import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
 
import com.dao.PetCategoriesDAO;

import com.model.PetCategories;
 
@Service

public class PetCategoriesService

{

	@Autowired 

	PetCategoriesDAO petCategoriesDAO;

	public ResponseEntity<?> getAllCategories()

	{

        List<PetCategories> categories = petCategoriesDAO.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

	public ResponseEntity<?> getCategoryById(int categoryId) 

	{

        Optional<PetCategories> category = petCategoriesDAO.findById(categoryId);

        if (category.isPresent()) 

        {

            return new ResponseEntity<>(category.get(), HttpStatus.OK);

        }

        else 

        {

            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);

        }

    }

	public ResponseEntity<?> getCategoryByName(String categoryName)

	{

        Optional<PetCategories> category = petCategoriesDAO.findByName(categoryName);

        if (category.isPresent()) 

        {

            return new ResponseEntity<>(category.get(), HttpStatus.OK);

        } else

        {

            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);

        }

    }


	public ResponseEntity<?> addCategory(PetCategories category) 

	{

        try 

        {

            if (category == null || category.getName() == null || category.getName().isEmpty()) 

            {

                return new ResponseEntity<>("Category name cannot be empty", HttpStatus.BAD_REQUEST);

            }

            if (petCategoriesDAO.findByName(category.getName()).isPresent())

            {

                return new ResponseEntity<>("Category with this name already exists",HttpStatus.CONFLICT);

            }
 
            PetCategories savedCategory = petCategoriesDAO.save(category);

            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

        }

        catch (Exception e) 

        {

            return new ResponseEntity<>("Failed to add category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

	public ResponseEntity<?> updateCategory(int categoryId, PetCategories updatedCategory)

	{

        Optional<PetCategories> existingCategoryOptional = petCategoriesDAO.findById(categoryId);
 
        if (existingCategoryOptional.isPresent()) 

        {

            try {

                if (updatedCategory == null) 

                {

                    return new ResponseEntity<>("Updated category cannot be null", HttpStatus.BAD_REQUEST);

                }
 
                PetCategories existingCategory = existingCategoryOptional.get();              

                existingCategory.setName(updatedCategory.getName());         

                PetCategories savedCategory = petCategoriesDAO.save(existingCategory);

                return new ResponseEntity<>(savedCategory, HttpStatus.OK);

            } 

            catch (Exception e) 

            {

                return new ResponseEntity<>("Failed to update category: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }

        } 

        else 

        {

            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);

        }

    }
 
}
 
 