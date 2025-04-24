package com.database.service;

import java.util.List;

import com.database.entity.Ambulance;

public interface IAmbulanceService {

	//Returns Ambulance Number if successful, otherwise returns null
	public String saveAmbulance(Ambulance ambulance);
	
	//Get ambulance details by vehicle number, if exists returns details, otherwise null
	public Ambulance findByVehicleNumber(String vehicleNumber);
	
	//Find all Ambulance details, if exists returns details, otherwise null
	public List<Ambulance> findAllAmbulanceDetails();
	
	//Update AmbulanceDetails. If updated succeed returns vehicle number otherwise returns null
	public String updateAmbulanceDetails(Ambulance ambulance);
	
	//Delete Ambulance by vehicle number, returns vehicle number if succeed, otherwise null
	public String deleteAmbulanceDetails(String vehicleNumber);
	
	//To update ambulance status
	public String updateStatus(String vehicleNumber, boolean status);
	
	//Get all available ambulances
	public List<String> getAvailableAmbulance();
}
