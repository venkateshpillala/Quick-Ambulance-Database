package com.database.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.database.entity.DriverLogs;
import com.database.projection.IVehicleAndPhoneProjection;

public interface IDriverLogsDao extends JpaRepository<DriverLogs, Long> {

	Optional<List<DriverLogs>> findByDriverUsername(String username);

	Optional<List<DriverLogs>> findByAmbulanceVehicleNumber(String vehicleNumber);

	Optional<DriverLogs> findByDriverUsernameAndLoginDate(String username, LocalDate date);

	Optional<DriverLogs> findByAmbulanceVehicleNumberAndLoginDate(String vehicleNumber, LocalDate date);

	Optional<DriverLogs> findByDriverUsernameAndAmbulanceVehicleNumberAndLoginDate(String username,
			String vehicleNumber, LocalDate date);

	Optional<List<DriverLogs>> findByLoginDate(LocalDate date);

	Optional<DriverLogs> findByDriverUsernameAndAmbulanceIsNullAndLogoutIsNull(String username);

	Optional<DriverLogs> findByDriverUsernameAndLogoutIsNull(String username);

	@Query("SELECT d.ambulance.vehicleNumber AS vehicleNumber, d.driver.phone AS phone " + "FROM DriverLogs d "
			+ "WHERE d.driver.username = :username AND d.loginDate = :date")
	public IVehicleAndPhoneProjection findVehicleInfoByUsernameAndDate(@Param("username") String username,
			@Param("date") LocalDate date);

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("UPDATE DriverLogs d SET d.ambulance.vehicleNumber = :vehicleNumber WHERE d.driver.username = :username AND d.loginDate = :loginDate"
	 * ) Integer updateVehicleNumberByUsernameAndDate(String username, LocalDate
	 * loginDate, String vehicleNumber);
	 * 
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("UPDATE DriverLogs dl SET dl.logout = :logout WHERE dl.driver.username = :username AND dl.loginDate = :loginDate"
	 * ) Integer updateLogoutTimeByUsernameAndDate(String username, LocalDate
	 * loginDate, LocalTime logout);
	 */

}
