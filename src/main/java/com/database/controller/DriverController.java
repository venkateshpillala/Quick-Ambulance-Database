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

import com.commons.dto.DriverDTO;
import com.database.entity.Driver;
import com.database.service.IDriverService;
import com.database.service.ISaveAndDeleteService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private ISaveAndDeleteService sadDriverService;
	
	@Autowired
	private IDriverService driverService;
	
	@PostMapping
	public ResponseEntity<String> saveDriver(@RequestBody DriverDTO driverDto){
		String status = sadDriverService.saveDriver(driverDto);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteDriver(@RequestParam String username){
		String status = sadDriverService.deleteDriver(username);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping("/find-all")
	public ResponseEntity<List<Driver>> findAllDrivers(){
		List<Driver> list = driverService.findAllDriverDetails();
		return new ResponseEntity<List<Driver>>(list, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Driver> findByUsername(@RequestParam String username){
		Driver driver = driverService.findDriverByUsername(username);
		return new ResponseEntity<Driver>(driver, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<String> updateDriverDetails(@RequestBody Driver driver){
		String status = driverService.updateDriverDetails(driver);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PostMapping("/update-status")
	public ResponseEntity<String> updateDriverStatus(@RequestParam String username,
													@RequestParam boolean status){
		String stat = driverService.updateDriverStatus(username, status);
		return new ResponseEntity<String>(stat, HttpStatus.OK);
	}
	
}
