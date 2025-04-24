package com.database.service;

import java.time.LocalDate;
import java.util.List;

import com.database.entity.DriverLogs;

public interface IDriverLogsService {

	public List<DriverLogs> findByUsername(String username);
	public List<DriverLogs> findByVehicleNumber(String vehicleNumber);
	public DriverLogs findByUsesrnameAndDate(String username, LocalDate date);
	public DriverLogs findByVehicleNumberAndDate(String vehicleNumber, LocalDate date);
	public DriverLogs findByUsernameVehicleNumberAndDate(String username, String vehicleNumber, LocalDate date);
	public List<DriverLogs> findByDate(LocalDate date);
	public Integer updateVehicleNumberByUsername(String username, String vehicleNumber);
	public Long updateLogoutByUsername(String username);
	public Long updateLogin(String username);
	
}
