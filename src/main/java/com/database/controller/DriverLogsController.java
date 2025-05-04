package com.database.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.entity.DriverLogs;
import com.database.projection.IVehicleAndPhoneProjection;
import com.database.service.IDriverLogsService;

@RestController
@RequestMapping("/driver-logs")
public class DriverLogsController {

	@Autowired
	private IDriverLogsService driverLogService;
	
	@GetMapping("/username")
	public ResponseEntity<List<DriverLogs>> getDriverLogsByUsername(@RequestParam String username){
		List<DriverLogs> list = driverLogService.findByUsername(username);
		return new ResponseEntity<List<DriverLogs>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/vehicle-number")
	public ResponseEntity<List<DriverLogs>> getDriverLogsByVehicleNumber(@RequestParam String vehicleNumber){
		List<DriverLogs> list = driverLogService.findByVehicleNumber(vehicleNumber);
		return new ResponseEntity<List<DriverLogs>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/username-date")
	public ResponseEntity<DriverLogs> getDriverLogsByUsernameAndDate(@RequestParam String username, @RequestParam LocalDate date){
		DriverLogs dl = driverLogService.findByUsesrnameAndDate(username, date);
		return new ResponseEntity<DriverLogs>(dl, HttpStatus.OK);
	}
	
	@GetMapping("/vehicleNumber-date")
	public ResponseEntity<DriverLogs> getDriverLogsByVehicleNumberAndDate(@RequestParam String vehicleNumber, @RequestParam LocalDate date){
		DriverLogs dl = driverLogService.findByVehicleNumberAndDate(vehicleNumber, date);
		return new ResponseEntity<DriverLogs>(dl, HttpStatus.OK);
	}
	
	@GetMapping("/username-vehicleNumber-date")
	public ResponseEntity<DriverLogs> getByUsernameVehicleNumberAndDate(@RequestParam String username, @RequestParam String vehicleNumber, @RequestParam LocalDate date){
		DriverLogs dl = driverLogService.findByUsernameVehicleNumberAndDate(username, vehicleNumber, date);
		return new ResponseEntity<DriverLogs>(dl, HttpStatus.OK);
	}
	
	@GetMapping("/date")
	public ResponseEntity<List<DriverLogs>> getByDate(@RequestParam LocalDate date){
		List<DriverLogs> list = driverLogService.findByDate(date);
		return new ResponseEntity<List<DriverLogs>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/update-vehicle")
	public ResponseEntity<Integer> updateVehicleNumber(@RequestParam String username, 
													@RequestParam String vehicleNumber){
		Integer status = driverLogService.updateVehicleNumberByUsername(username, vehicleNumber);
		return new ResponseEntity<Integer>(status, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Long> newLogin(@RequestParam String username){
		Long status = driverLogService.updateLogin(username);
		return new ResponseEntity<Long>(status, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Long> updateLogout(@RequestParam String username){
		Long status = driverLogService.updateLogoutByUsername(username);
		return new ResponseEntity<Long>(status, HttpStatus.OK);
	}
	
	@GetMapping("/vehicle-phone")
	public ResponseEntity<Map<String, Object>> getDriverPhoneAndVehicleNumber(@RequestParam String username){
		Map<String, Object> response = new HashMap<String, Object>();
		IVehicleAndPhoneProjection vp = driverLogService.getVehicleNumberAndPhone(username);
		response.put("vehicleNumber", vp.getVehicleNumber());
		response.put("phone", vp.getPhone());
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
				
	}
	
}
