package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.dao.IDriverDao;
import com.database.entity.Driver;
import com.database.exception.DuplicateResourceException;
import com.database.exception.ResourceNotFoundException;

@Service
public class DriverServiceImple implements IDriverService {

	@Autowired
	private IDriverDao dao;

	private String status;
	private Driver driv;

	@Override
	public String saveDriverDetails(Driver driver) {
		
		dao.findById(driver.getUsername().toLowerCase()).ifPresent(a -> {
	        throw new DuplicateResourceException("RESOURCE_EXISTED_WITH_USERNAME " + driver.getUsername());
	    	});
		
		driv = dao.save(driver);
		if (driv != null) 
			status = driv.getUsername();
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN....!");
		return status;
	}

	@Override
	public Driver findDriverByUsername(String username) {
		driv = dao.findById(username.toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("DRIVER_NOT_AVAILABLE_WITH_ID "+username)
				);

		return driv;
	}

	@Override
	public List<Driver> findAllDriverDetails() {
		return dao.findAll();
	}

	@Override
	public String updateDriverDetails(Driver driver) {
		Driver driv = null;
		if (driver != null) {
			driv = this.updateNullValues(driver);
			status = dao.save(driv).getUsername();
		}
		return status;
	}

	@Override
	public String deleteDriverByUsername(String username) {
		Driver driv = dao.findById(username.toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username)
				);
		if(driv != null) {
			dao.delete(driv);
			status = username;
		}
		else {
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN....!");
		}
			
		return status;
	}
	
	@Override
	public String updateDriverStatus(String username, boolean st) {
		Integer result = dao.updateDriverStatusByUsername(username.toLowerCase(), st);
		if(result != null)
			status = username;
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG.....!");
		return status;
	}
	
	private Driver updateNullValues(Driver driver) {
		Driver driv = dao.findById(driver.getUsername().toLowerCase()).orElseThrow(
					()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+driver.getUsername())
				);
		
		if(driv != null) {
			if(driver.getStatus() == null)
				driver.setStatus(driv.getStatus());
			if(driver.getDob() == null)
				driver.setDob(driv.getDob());
			if(driver.getDriverName() == null)
				driver.setDriverName(driv.getDriverName());
			if(driver.getEmail() == null)
				driver.setEmail(driv.getEmail());
			if(driver.getLicense() == null)
				driver.setLicense(driv.getLicense());
			if(driver.getPhone() == null)
				driver.setPhone(driv.getPhone());
			if(driver.getUsername() == null)
				driver.setUsername(driv.getUsername());
		}
		
		return driver;
	}


}
