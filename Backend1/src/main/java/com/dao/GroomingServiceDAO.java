package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.GroomingService;

public interface GroomingServiceDAO extends JpaRepository<GroomingService,Integer> {
	List<GroomingService> findByServiceId(int serviceId);
	
	 List<GroomingService> findByName(String name);

	List<GroomingService> findByDescription(String description);
	List<GroomingService> findByPrice(float price);
	List<GroomingService> findByAvailable(boolean available);
	
	
	
	
	
}