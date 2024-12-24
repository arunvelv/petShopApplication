package com.dao;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.model.Pets;
 
@Repository
public interface PetsDAO extends JpaRepository<Pets,Integer>
{
	List<Pets>findAll();
	Optional<Pets> findById(Integer id);
	Optional<Pets> findByName(String name);
	
	@Query("SELECT p FROM Pets p WHERE p.category.name = :category")
    List<Pets> findByCategoryName(String category);
	
	 @Query("SELECT p FROM Pets p JOIN FETCH p.grooming_services WHERE p.petId = :petId")
	 Pets findPetsWithGroomingServices(int petId);

	 @Query("SELECT p FROM Pets p JOIN FETCH p.vaccinations WHERE p.petId = :petId")
	 Pets findPetsWithVaccinations(int petId);
	 
	 @Query("SELECT p FROM Pets p JOIN FETCH p.suppliers WHERE p.petId = :petId")
	 Pets findPetsWithSuppliers(int petId);
	 
	@Query("SELECT p FROM Pets p JOIN FETCH p.pet_food WHERE p.petId = :petId")
	Pets findPetsWithFoodInfo(int petId);
	
    @Query("SELECT p FROM Pets p JOIN FETCH p.transactions WHERE p.petId = :petId")
    Pets findPetsWithTransactionHistory(int petId);
    
    @Query("SELECT p FROM Pets p JOIN FETCH p.employees WHERE p.petId = :petId")
    Optional<Pets> findPetWithEmployees(int petId);
 
 
	 
}