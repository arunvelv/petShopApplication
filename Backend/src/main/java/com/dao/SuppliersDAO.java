	package com.dao;
	 
	import java.util.List;
	 
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Suppliers;
	 
	@Repository
	public interface SuppliersDAO extends JpaRepository<Suppliers,Integer>{
		
		List<Suppliers> findAll();
		
		Suppliers findBySuppliersId(int SuppliersId);
		
		List<Suppliers> findByName(String name);
		
		@Query("select s from Suppliers s where s.address.city= :city")
	    List<Suppliers> findByCity(@Param("city") String city)	;
		
		@Query("select s from Suppliers s where s.address.state= :state")
	    List<Suppliers> findByState(@Param("state") String state);
		
		List<Suppliers> findByAddressAddressId(int addressId);
	 
	}
	 
	 