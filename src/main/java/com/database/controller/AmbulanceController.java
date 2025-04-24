package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.entity.Ambulance;
import com.database.service.IAmbulanceService;

@RestController
@RequestMapping("/ambulance")
public class AmbulanceController {
	
	@Autowired
	private IAmbulanceService service;

	@GetMapping
	public ResponseEntity<Ambulance> findVehicleByVehicleNumber(@RequestParam String vehicleNumber){
		Ambulance ambulance = service.findByVehicleNumber(vehicleNumber);
		return new ResponseEntity<Ambulance>(ambulance, HttpStatus.OK);
	}
	
	@GetMapping("/find-all")
	public ResponseEntity<List<Ambulance>> findAllAmbulanceDetails(){
		List<Ambulance> list = service.findAllAmbulanceDetails();
		return new ResponseEntity<List<Ambulance>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteVehicleByVehicleNumber(@RequestParam String vehicleNumber){
		String status = service.deleteAmbulanceDetails(vehicleNumber);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> saveVehicleDetails(@RequestBody Ambulance ambulance){
		String status = service.saveAmbulance(ambulance);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<String> updateVehicleDetails(@RequestBody Ambulance ambulance){
		String status = service.updateAmbulanceDetails(ambulance);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PostMapping("/update-status")
	public ResponseEntity<String> updateAmbulanceStatus(@RequestParam String vehicleNumber, 
														@RequestParam boolean status){
		String result = service.updateStatus(vehicleNumber, status);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<String>> getAvailableAmbulances(){
		List<String> list = service.getAvailableAmbulance();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
}
