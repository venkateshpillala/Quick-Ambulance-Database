package com.database.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.exception.ResourceNotFoundException;
import com.database.dao.IAmbulanceDao;
import com.database.dao.IDriverDao;
import com.database.dao.IDriverLogsDao;
import com.database.entity.Ambulance;
import com.database.entity.Driver;
import com.database.entity.DriverLogs;

@Service
public class DriverLogsServiceImple implements IDriverLogsService {

	@Autowired
	private IDriverLogsDao dao;

	@Autowired
	private IDriverDao driverDao;
	
	@Autowired
	private IAmbulanceDao ambulanceDao;

	@Override
	public List<DriverLogs> findByUsername(String username) {
		return dao.findByDriverUsername(username.toLowerCase())
				.filter(logs -> !logs.isEmpty())
				.orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username)
				);
	}

	@Override
	public List<DriverLogs> findByVehicleNumber(String vehicleNumber) {
		return dao.findByAmbulanceVehicleNumber(vehicleNumber.toUpperCase())
				.filter(logs -> !logs.isEmpty())
				.orElseThrow(
				() ->new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_VEHICLE_NUMBER "+vehicleNumber)
				);
	}

	@Override
	public DriverLogs findByUsesrnameAndDate(String username, LocalDate date) {
		return dao.findByDriverUsernameAndLoginDate(username.toLowerCase(), date).orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME_AND_DATE "+username+" "+date)
				);
	}

	@Override
	public DriverLogs findByVehicleNumberAndDate(String vehicleNumber, LocalDate date) {
		return dao.findByAmbulanceVehicleNumberAndLoginDate(vehicleNumber.toUpperCase(), date).orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_VEHICLE_NUMBER_AND_DATE "+vehicleNumber+" "+date)
				);
	}

	@Override
	public DriverLogs findByUsernameVehicleNumberAndDate(String username, String vehicleNumber, LocalDate date) {
		return dao.findByDriverUsernameAndAmbulanceVehicleNumberAndLoginDate(username.toLowerCase(), vehicleNumber.toUpperCase(), date).orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME_VEHICLE_NUMBER_AND_DATE "+username+" "+vehicleNumber+" "+date)
				);
	}

	@Override
	public List<DriverLogs> findByDate(LocalDate date) {
		return dao.findByLoginDate(date)
				.filter(logs -> !logs.isEmpty())
				.orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_DATE "+ date)
				);
	}

	@Override
	public Integer updateVehicleNumberByUsername(String username, String vehicleNumber) {
		Integer status = 0;
		LocalDate date = LocalDate.now();
		DriverLogs dl = dao.findByDriverUsernameAndAmbulanceIsNullAndLogoutIsNull(username.toLowerCase()).orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username+" "+vehicleNumber+" "+date)
				);
		Ambulance amb = ambulanceDao.findById(vehicleNumber.toUpperCase()).orElseThrow(
				()->{
					throw new ResourceNotFoundException("RESOURCE_NOT_AVAILABLE_WITH_VEHICLE_NUMBER "+vehicleNumber);
				});

		if (dl != null) {
			dl.setAmbulance(amb);
			dao.save(dl);
			status = 1;
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");

		return status;
	}

	@Override
	public Long updateLogoutByUsername(String username) {
		Long status = 0L;
		DriverLogs dl = dao.findByDriverUsernameAndLogoutIsNull(username.toLowerCase()).orElseThrow(
				() -> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username+" ")
				);
		if (dl != null) {
			dl.setLogout(LocalTime.now());
			status = dao.save(dl).getLogid();
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");
		return status;
	}

	@Override
	public Long updateLogin(String username) {
		Driver driver = null;
		DriverLogs dl = null;
		Long status = 0L;
		
		driver = driverDao.findById(username.toLowerCase()).orElseThrow(
				()-> new ResourceNotFoundException("RESOURCE_NOT_FOUND_WITH_USERNAME "+username)
				);
		
		if (driver != null) {
			dl = new DriverLogs();
			dl.setLoginDate(LocalDate.now());
			dl.setDriver(driver);
			dl.setLogin(LocalTime.now());
			status = dao.save(dl).getLogid();
		}
		else
			throw new RuntimeException("SOMETHING_WENT_WRONG_TRY_AGAIN...!");

		return status;
	}

}
