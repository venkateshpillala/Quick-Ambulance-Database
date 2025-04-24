package com.database.service;

import java.util.List;

import com.database.entity.LiveLocation;

public interface ILiveLocationService {

	public String saveAndUpdateDriverLiveLocation(LiveLocation driverLiveLocation);
	public List<LiveLocation> getAllDriverLiveLocation();
	public String deleteDriverLocation(String username);
}
