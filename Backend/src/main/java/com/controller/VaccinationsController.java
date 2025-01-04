package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Vaccinations;
import com.service.VaccinationsService;

@RestController
@CrossOrigin("*")
public class VaccinationsController {
	
	@Autowired
	VaccinationsService VaccinationsService;
	
	@PostMapping("/api/v1/vaccinations/add")
	public ResponseEntity<?> addVaccinations(@RequestBody Vaccinations v)
	{
		VaccinationsService.addVaccinations(v);
		return new ResponseEntity<String>("Vaccination added",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/v1/vaccinations/{vaccination_id}")
	public ResponseEntity<?> getVaccinationsById(@PathVariable int vaccination_id)
	{
		Vaccinations v = VaccinationsService.getById(vaccination_id);
		return new ResponseEntity<Vaccinations>(v,HttpStatus.OK);
	}
	
    @GetMapping("/api/v1/vaccinations/available")
	public ResponseEntity<?> getAvailableVaccinations()
	{
		List<Vaccinations> list = VaccinationsService.findAvailableVaccinations();
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
    
    @GetMapping("/api/v1/vaccinations/unavailable")
	public ResponseEntity<?> getUnavailableVaccinations()
	{
		List<Vaccinations> list = VaccinationsService.findUnavailableVaccinations();
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
    
    @PutMapping("/api/v1/vaccinations/update/{vaccination_id}")
	public ResponseEntity<?> updateVaccinations(@RequestBody Vaccinations v,@PathVariable int vaccination_id)
	{
		VaccinationsService.updateVaccinations(vaccination_id,v);
		return new ResponseEntity<String>("Vaccinations updated",HttpStatus.OK);
	}
    
    @GetMapping("/api/v1/vaccinations")
	public ResponseEntity<?> getAll()
	{
		List<Vaccinations> list = VaccinationsService.getAll();
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
	}
