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
@RestController// Marks this class as a REST controller to handle HTTP requests and produce JSON or XML responses.
//@CrossOrigin("*")// Allows cross-origin requests from any domain. "*" means all origins are allowed.
public class VaccinationsController {
	@Autowired // Injects the VaccinationService dependency automatically.
 
	VaccinationsService VaccinationsService; // A reference to the service layer that handles business logic.
	@PostMapping("/api/v1/vaccinations/add")
	public ResponseEntity<?> addVaccinations(@RequestBody Vaccinations v)//The vaccination data is passed in the request body.
	{
		VaccinationsService.addVaccinations(v);// Calls the service layer to add the vaccination to the database.
		return new ResponseEntity<String>("Vaccination added",HttpStatus.ACCEPTED);// Returns a response with a success message and HTTP status 202 (Accepted).
	}
	@GetMapping("/api/v1/vaccinations/{vaccination_id}")
	public ResponseEntity<?> getVaccinationsById(@PathVariable int vaccination_id)// Calls the service layer to fetch the vaccination by ID.
	{
//		Vaccinations v = VaccinationsService.getById(vaccination_id);
//		return new ResponseEntity<Vaccinations>(v,HttpStatus.OK);
		Vaccinations vaccination = VaccinationsService.getById(vaccination_id);
	    if (vaccination == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccination Not Found");
	    }
	    return ResponseEntity.ok(vaccination);
	}
    @GetMapping("/api/v1/vaccinations/available")
	public ResponseEntity<?> getAvailableVaccinations()// Retrieves all vaccinations that are marked as available.
	{
		List<Vaccinations> list = VaccinationsService.findAvailableVaccinations();// Calls the service layer to fetch all available vaccinations.
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
    @GetMapping("/api/v1/vaccinations/unavailable")
	public ResponseEntity<?> getUnavailableVaccinations()// Retrieves all vaccinations that are marked as unavailable.
	{
		List<Vaccinations> list = VaccinationsService.findUnavailableVaccinations();
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
    @PutMapping("/api/v1/vaccinations/update/{vaccination_id}")
	public ResponseEntity<?> updateVaccinations(@RequestBody Vaccinations v,@PathVariable int vaccination_id)// Updates an existing vaccination. The data is passed in the request body, and the ID is extracted from the URL
	{
//		VaccinationsService.updateVaccinations(vaccination_id,v);
//		return new ResponseEntity<String>("Vaccinations updated",HttpStatus.OK);
    	Vaccinations updatedVaccination = VaccinationsService.updateVaccinations(vaccination_id, v);
        if (updatedVaccination == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaccination not found");
        }
        return ResponseEntity.ok("Vaccinations updated");
	}
    @GetMapping("/api/v1/vaccinations")
	public ResponseEntity<?> getAll()// Retrieves all vaccinations from the database.
	{
		List<Vaccinations> list = VaccinationsService.getAll();// Calls the service layer to fetch all vaccinations.
		return new ResponseEntity<List<Vaccinations>>(list,HttpStatus.OK);
	}
	}