package com.dao;
 
import java.util.List;

import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.model.PetFood;
 
import jakarta.transaction.Transactional;
 
@Repository

public interface PetFoodDAO extends JpaRepository<PetFood,Integer>

{

	//Get all PetFood

	List<PetFood> findAll();

	 //Get PetFood by food_id

	Optional<PetFood> findById(Integer id);

	//Get PetFood by name

    List<PetFood> findByName(String name);

    // Get all PetFood by type

    List<PetFood> findByType(String type);

    //Get all PetFood by brand name

    List<PetFood> findByBrand(String brand);

    //Update PetFood quantity by food_id

    @Modifying

    @Transactional

    @Query("UPDATE PetFood pf SET pf.quantity = :quantity WHERE pf.foodId = :foodId")

    void updateQuantityByFoodId(int quantity, int foodId);

    @Modifying
    @Transactional
    @Query("UPDATE PetFood pf SET pf.name = :name, pf.brand = :brand, pf.type = :type, pf.quantity = :quantity, pf.price = :price, pf.imageURL = :imageURL WHERE pf.foodId = :foodId")
    void updatePetFoodById(
        @Param("name") String name,
        @Param("brand") String brand,
        @Param("type") String type,
        @Param("quantity") int quantity,
        @Param("price") float price,
        @Param("imageURL") String imageURL,
        @Param("foodId") int foodId
    );
	
 
}

 