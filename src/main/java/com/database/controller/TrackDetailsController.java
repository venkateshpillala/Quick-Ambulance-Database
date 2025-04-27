package com.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.dto.TrackDetailsDTO;
import com.database.entity.TrackDetails;
import com.database.service.ITrackDetailsService;

@RestController
@RequestMapping("/track-details")
public class TrackDetailsController {
	
	@Autowired
	private ITrackDetailsService service;

	@PostMapping
	public ResponseEntity<Long> saveTrackDetails(@RequestBody TrackDetailsDTO dto){
		Long status = service.saveBookingDetails(dto);
		return new ResponseEntity<Long>(status, HttpStatus.OK);
	}
	
	@GetMapping("/user-history")
	public ResponseEntity<List<TrackDetails>> getUserHistory(@RequestParam String username){
		List<TrackDetails> list = service.userHistory(username);
		return new ResponseEntity<List<TrackDetails>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/driver-history")
	public ResponseEntity<List<TrackDetails>> getDriverHistory(@RequestParam String username){
		List<TrackDetails> list = service.driverHistory(username);
		return new ResponseEntity<List<TrackDetails>>(list, HttpStatus.OK);
	}
}
