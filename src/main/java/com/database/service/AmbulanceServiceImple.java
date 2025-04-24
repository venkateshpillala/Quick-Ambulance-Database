package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.database.dao.IAmbulanceDao;
import com.database.entity.Ambulance;
import com.database.exception.DuplicateResourceException;
import com.database.exception.ResourceNotFoundException;

/*
 * This class provides all the required services of Ambulance database
 */

@Service
public class AmbulanceServiceImple implements IAmbulanceService {

	@Autowired
	private IAmbulanceDao dao;
	
	private Ambulance amb;
	private String status;
	
	@Override
	public String saveAmbulance(Ambulance ambulance) {
		
		if(ambulance != null) {
			dao.findById(ambulance.getVehicleNumber()).ifPresent(a -> {
			
		throw new DuplicateResourceException("AMBULANCE_EXISTED_VEHICLE_NUMBER "+ambulance.getVehicleNumber());});
			
				ambulance.setVehicleNumber(ambulance.getVehicleNumber().toUpperCase());
				ambulance.setStatus(false);
				ambulance.setCbook(this.convertMultipartFileIntoBytes(ambulance.getBook()));
				amb = dao.save(ambulance);
		}
		if(amb != null) 
			status = amb.getVehicleNumber();
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN....!");
		
		return status;
	}

	@Override
	public Ambulance findByVehicleNumber(String vehicleNumber) {

		if(vehicleNumber != null) {
			amb = dao.findById(vehicleNumber.toUpperCase()).orElseThrow(() ->
						new ResourceNotFoundException("VEHICLE_NOT_FOUND_WITH_VEHICLE_NUMBER "+ vehicleNumber)
					);
		}
		
		return amb;
	}

	@Override
	public List<Ambulance> findAllAmbulanceDetails() {
		return dao.findAll();
	}

	@Override
	public String updateAmbulanceDetails(Ambulance ambulance) {
		
		if(ambulance != null) {
			Ambulance amb = this.updateNullValues(ambulance);
			status = dao.save(amb).getVehicleNumber();
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");

		return status;
	}

	@Override
	public String deleteAmbulanceDetails(String vehicleNumber) {
		
		amb = dao.findById(vehicleNumber.toUpperCase()).orElseThrow(()->new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_VEHICLE_NUMBER "+vehicleNumber));
		if(amb != null) {
			dao.delete(amb);
			status = vehicleNumber;
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		return status;
	}
	
	private byte[] convertMultipartFileIntoBytes(MultipartFile file) {
		byte[] binary = null;
		try {
			if(file != null)
				binary = file.getBytes();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return binary;
	}
	
	private Ambulance updateNullValues(Ambulance ambulance) {
		
		amb = dao.findById(ambulance.getVehicleNumber()).orElseThrow(() ->
				new ResourceNotFoundException("VEHICLE_NOT_FOUND_WITH_VEHICLE_NUMBER "+ ambulance.getVehicleNumber())
				);
		
		if(ambulance.getStatus() == null)
			ambulance.setStatus(amb.getStatus());
		if(ambulance.getCbook() == null)
			ambulance.setCbook(amb.getCbook());
		if(ambulance.getColor() == null)
			ambulance.setColor(amb.getColor());
		if(ambulance.getCompany() == null)
			ambulance.setCompany(amb.getCompany());
		if(ambulance.getVehicleNumber() == null)
			ambulance.setVehicleNumber(amb.getVehicleNumber());
		if(ambulance.getYearOfModel() == null)
			ambulance.setYearOfModel(amb.getYearOfModel());
			
		return ambulance;
	}

	@Override
	public String updateStatus(String vehicleNumber, boolean s) {
		dao.findById(vehicleNumber.toUpperCase()).orElseThrow(() ->
		new ResourceNotFoundException("VEHICLE_NOT_FOUND_WITH_VEHICLE_NUMBER "+ vehicleNumber)
		);
		Integer result = dao.updateStatusByVehicleNumber(vehicleNumber.toUpperCase(), s);
		if(result != 0)
			status = vehicleNumber;
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		
		return status;
	}

	@Override
	public List<String> getAvailableAmbulance() {
		return dao.findAllAvailableAmbulanceNumbers().filter(logs -> !logs.isEmpty())
				.orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND")
				);
	}

}
