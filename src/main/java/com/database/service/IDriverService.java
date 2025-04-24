package com.database.service;

import java.util.List;

import com.database.entity.Driver;

public interface IDriverService {

	//save Driver details, if succeed returns username otherwise null
	public String saveDriverDetails(Driver driver);
	
	//Get Driver details by username, if exists return details otherwise null
	public Driver findDriverByUsername(String username);
	
	//Get all the Drivers details, if exists return details otherwise null
	public List<Driver> findAllDriverDetails();
	
	//Update Driver details, if succeed returns username, otherwise null
	public String updateDriverDetails(Driver driver);
	
	//Delete Driver by username, if succeed returns username, otherwise null
	public String deleteDriverByUsername(String username);
	
	//Update Driver status
	public String updateDriverStatus(String username, boolean status);
}
