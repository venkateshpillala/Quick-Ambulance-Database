package com.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.database.entity.Driver;

public interface IDriverDao extends JpaRepository<Driver, String> {

	@Modifying
	@Transactional
	@Query("UPDATE Driver d SET d.status = :status WHERE d.username = :username")
	Integer updateDriverStatusByUsername(String username, boolean status);

}
