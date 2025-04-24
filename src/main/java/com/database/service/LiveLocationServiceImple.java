package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.dao.ILiveLocationDao;
import com.database.entity.LiveLocation;
import com.database.exception.ResourceNotFoundException;

@Service
public class LiveLocationServiceImple implements ILiveLocationService {

	@Autowired
	private ILiveLocationDao dao;
	
	@Override
	public String saveAndUpdateDriverLiveLocation(LiveLocation driverLiveLocation) {
		String status = dao.save(driverLiveLocation).getUsername();
		return status;
	}

	@Override
	public List<LiveLocation> getAllDriverLiveLocation() {
		return dao.findAll();
	}

	@Override
	public String deleteDriverLocation(String username) {
		dao.findById(username.toLowerCase()).orElseThrow(()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_"+username));
		dao.deleteById(username.toLowerCase());
		return username;
	}
	
	

}
