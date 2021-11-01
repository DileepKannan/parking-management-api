package com.elsevier.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsevier.parking.model.Parking;
import com.elsevier.parking.repositories.ParkingRepo;


@RestController
public class ParkingManagementController { 

	@Autowired
	private ParkingRepo repo;

	@CrossOrigin(origins = {"http://localhost:8080", "https://parking-management-ui.herokuapp.com"})
	@PostMapping("/parking")
	public Parking addParking(@RequestBody Parking parking) {
		parking.setIsActive("Active"); 
		return repo.save(parking);
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://parking-management-ui.herokuapp.com"})
	@GetMapping("/parkings")
	public List<Parking> getAllParking() {
		return repo.findOpenParking();
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://parking-management-ui.herokuapp.com"})
	@GetMapping("/vehicle")
	public List<Parking> getVehicle(@RequestParam final String term) {
		return repo.findDocumentsForListing(term);
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://parking-management-ui.herokuapp.com"})
	@PostMapping("/updateParking")
	public Parking updateParking(@RequestBody Parking parking) { 
		//int repo.findDocumentsForUpdate(parking.getParkingId(), parking.getTotalHours(), parking.getClosingTime(), parking.getIsActive());
		Parking parking2 = repo.getById(parking.getParkingId());
		parking2.setIsActive("Inactive");
		parking2.setClosingTime(parking.getClosingTime());
		parking2.setTotalHours(parking.getTotalHours());
		
		return repo.save(parking2);
	} 


}
