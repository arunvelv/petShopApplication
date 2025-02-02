	package com.controller;
	 
	import com.exception.InvalidInputException;
import com.model.*;
	import com.service.SuppliersService;
	 
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	 
	import java.util.List;
	 
	@RestController
	@CrossOrigin("*")
	@RequestMapping("/api/v1/suppliers")
	public class SuppliersController {
	 
	    @Autowired
	    private SuppliersService suppliersService;
	 
	    @GetMapping
	    public ResponseEntity<List<Suppliers>> getAllSuppliers() {
	        List<Suppliers> suppliers =suppliersService.getAllSuppliers();
	        return new ResponseEntity<>(suppliers, HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id:[0-9]+}")
	    public ResponseEntity<Suppliers> getSuppliersById(@PathVariable("id") int suppliersId) {
	    	Suppliers suppliers = suppliersService.getSuppliersById(suppliersId);
	        if (suppliers != null) {
	            return new ResponseEntity<>(suppliers, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	    @GetMapping("/name/{name}")
	    public ResponseEntity<List<Suppliers>> getsuppliersByName(
	            @PathVariable("name") String name) {
	        List<Suppliers> suppliers= suppliersService.findSuppliersByName(name);
	        return new ResponseEntity<>(suppliers, HttpStatus.OK);
	    }
	 
	 
	    @GetMapping("/city/{city}")
	    public ResponseEntity<?> getByCity(@PathVariable String city) {
	        return suppliersService.findSupplierByCity(city);
	    }
	 
	    @GetMapping("/state/{state}")
	    public ResponseEntity<?> getByState(@PathVariable String state) {
	        return suppliersService.findSupplierByState(state);
	    }
	 
	 
	    @PostMapping("/add")
	    public ResponseEntity<Suppliers> addSuppliers(@RequestBody SupplierPayload payload) {
	    	Address address = payload.getAddress();
	        Suppliers suppliers = payload.getSuppliers();
//	        if (address == null || suppliers == null) {
//	            throw new InvalidInputException("VALIDATION_ERROR");
//	       }
	        List<Suppliers> existingSuppliers = suppliersService.findByAddress(address.getAddressId());
	        if (!existingSuppliers.isEmpty()) {
	           throw new InvalidInputException("ADD_FAILS");
	        }
	    	Suppliers createdSuppliers = suppliersService.saveSuppliers(suppliers, address);
	        return new ResponseEntity<>(createdSuppliers, HttpStatus.CREATED);
	        }
	    
	   
	      
	        
	        
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Suppliers> updateCustomer(@PathVariable("id") int suppliersId, @RequestBody Suppliers suppliersDetails) {
	    	Suppliers updatedSuppliers = suppliersService.updateSuppliers(suppliersId, suppliersDetails);
	        if (updatedSuppliers != null) {
	            return new ResponseEntity<>(updatedSuppliers, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	}
	 