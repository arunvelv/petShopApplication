package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.GroomingService;
import com.service.GroomingServiceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/services")
public class GroomingServiceController {
    
    @Autowired
    GroomingServiceService groomingServiceService;

    @PostMapping("/add")
    public ResponseEntity<?> addGroomingService(@RequestBody GroomingService groomingService) {
        return groomingServiceService.addGroomingService(groomingService);
    }

    @GetMapping
    public ResponseEntity<?> getAllGroomingServices() {
        return groomingServiceService.getAllGroomingServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByServiceId(@PathVariable("id") int serviceId) {
        return groomingServiceService.findByServiceId(serviceId);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getByAvailable() {
        return groomingServiceService.findByAvailable(true);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<?> getByUnavailable() {
        return groomingServiceService.findByAvailable(false);
    }

    @PutMapping("/update/{serviceId}")
    public ResponseEntity<?> updateService(@PathVariable("serviceId") int serviceId, @RequestBody GroomingService updatedService) {
        return groomingServiceService.updateGroomingService(serviceId, updatedService);
    }
}