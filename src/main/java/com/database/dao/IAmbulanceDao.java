package com.database.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.database.entity.Ambulance;

public interface IAmbulanceDao extends JpaRepository<Ambulance, String> {

	@Modifying
	@Transactional
	@Query("UPDATE Ambulance a SET a.status = :status WHERE a.vehicleNumber = :vehicleNumber")
	Integer updateStatusByVehicleNumber(String vehicleNumber, boolean status);
	
	@Modifying
	@Transactional
	@Query("SELECT a.vehicleNumber FROM Ambulance a WHERE a.status = false")
	Optional<List<String>> findAllAvailableAmbulanceNumbers();
	

}
