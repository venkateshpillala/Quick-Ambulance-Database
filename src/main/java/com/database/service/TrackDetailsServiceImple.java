package com.database.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.dao.ITrackDetailsDao;
import com.database.dto.TrackDetailsDTO;
import com.database.entity.Ambulance;
import com.database.entity.Driver;
import com.database.entity.TrackDetails;
import com.database.entity.User;
import com.database.exception.ResourceNotFoundException;

@Service
public class TrackDetailsServiceImple implements ITrackDetailsService {
	
	@Autowired
	private ITrackDetailsDao dao;

	@Override
	public Long saveBookingDetails(TrackDetailsDTO dto) {
		Long status = 0L;
		if(dto != null) {
			TrackDetails track = this.convertDTOToEntity(dto);
			status = dao.save(track).getTrackId();
		}
		else 
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		return status;
	}

	private TrackDetails convertDTOToEntity(TrackDetailsDTO dto) {
		TrackDetails entity = new TrackDetails();
		Ambulance ambulance = new Ambulance();
		User user = new User();
		Driver driver = new Driver();
		
		entity.setBookDate(LocalDate.now());
		entity.setBookTime(LocalTime.now());
		entity.setPickup(dto.getPickup());
		ambulance.setVehicleNumber(dto.getVehicleNumber().toUpperCase());
		entity.setAmbulance(ambulance);
		user.setUsername(dto.getUsername().toLowerCase());
		entity.setUser(user);
		driver.setUsername(dto.getDriverName().toLowerCase());
		entity.setDriver(driver);
		
		return entity;
	}

	@Override
	public List<TrackDetails> userHistory(String username) {
		return dao.findByUserUsername(username.toLowerCase())
				.filter(track -> !track.isEmpty())
				.orElseThrow(()-> new ResourceNotFoundException("NO_HISTORY_WITH "+username));
	}

	@Override
	public List<TrackDetails> driverHistory(String username) {
		return dao.findByDriverUsername(username.toLowerCase())
				.filter(track -> !track.isEmpty())
				.orElseThrow(() -> new ResourceNotFoundException("NO_HISTORY_WITH "+username));
	}
}
