package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.entity.LiveLocation;
import com.database.service.ILiveLocationService;

@RequestMapping("/driver-live-location")
@RestController
public class LiveLocationController {

	@Autowired
	private ILiveLocationService service;
	
	@PostMapping
	public ResponseEntity<String> saveDriverLiveLocation(@RequestBody LiveLocation driverLiveLocation){
		String status = service.saveAndUpdateDriverLiveLocation(driverLiveLocation);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<LiveLocation>> getAllDriverLiveLocations(){
		List<LiveLocation> dll =  service.getAllDriverLiveLocation();
		return new ResponseEntity<List<LiveLocation>>(dll, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteDriverLiveLocation(@RequestParam String username){
		String status = service.deleteDriverLocation(username);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
}
