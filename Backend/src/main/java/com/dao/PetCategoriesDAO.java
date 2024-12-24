package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.PetCategories;

public interface PetCategoriesDAO extends JpaRepository<PetCategories, Integer> {

    @Query("SELECT c FROM PetCategories c WHERE c.name = ?1")
    Optional<PetCategories> findByName(String name);

}
