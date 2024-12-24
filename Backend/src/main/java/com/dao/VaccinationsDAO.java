package com.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Vaccinations;

@Repository
public interface VaccinationsDAO extends JpaRepository<Vaccinations,Integer> {

	List<Vaccinations> findByAvailable(boolean available);


}
