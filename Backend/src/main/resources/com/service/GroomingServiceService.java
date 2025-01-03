package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.GroomingServiceDAO;
import com.model.GroomingService;

@Service
public class GroomingServiceService {
    
    @Autowired
    GroomingServiceDAO groomingServiceDAO;

    public ResponseEntity<?> addGroomingService(GroomingService groomingService) {
        if (groomingService == null || groomingService.getName() == null) {
            return new ResponseEntity<>("Invalid grooming service details", HttpStatus.BAD_REQUEST);
        }
        groomingServiceDAO.save(groomingService);
        return new ResponseEntity<>("Grooming service added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllGroomingServices() {
        List<GroomingService> groomingServices = groomingServiceDAO.findAll();
        if (groomingServices.isEmpty()) {
            return new ResponseEntity<>("No grooming services found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groomingServices, HttpStatus.OK);
    }

    public ResponseEntity<?> updateGroomingService(int serviceId, GroomingService updatedService) {
        if (updatedService == null) {
            return new ResponseEntity<>("Invalid service details", HttpStatus.BAD_REQUEST);
        }

        Optional<GroomingService> existingServiceOpt = groomingServiceDAO.findById(serviceId);
        if (existingServiceOpt.isPresent()) {
            GroomingService existingService = existingServiceOpt.get();
            existingService.setName(updatedService.getName());
            existingService.setDescription(updatedService.getDescription());
            existingService.setPrice(updatedService.getPrice());
            existingService.setAvailable(updatedService.isAvailable());
            groomingServiceDAO.save(existingService);
            return new ResponseEntity<>("Grooming service updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Grooming service not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>("Invalid service name", HttpStatus.BAD_REQUEST);
        }

        List<GroomingService> services = groomingServiceDAO.findByName(name);
        if (services.isEmpty()) {
            return new ResponseEntity<>("No grooming services found with the given name", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    public ResponseEntity<?> findByServiceId(int serviceId) {
        Optional<GroomingService> service = groomingServiceDAO.findById(serviceId);
        if (service.isEmpty()) {
            return new ResponseEntity<>("No grooming service found with the given ID", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.get(), HttpStatus.OK);
    }


    public ResponseEntity<?> findByAvailable(boolean available) {
        List<GroomingService> services = groomingServiceDAO.findByAvailable(available);
        if (services.isEmpty()) {
            String message = available ? "No available grooming services found" : "No unavailable grooming services found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}