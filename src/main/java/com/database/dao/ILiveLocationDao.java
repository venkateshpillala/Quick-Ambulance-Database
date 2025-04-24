package com.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.entity.LiveLocation;

public interface ILiveLocationDao extends JpaRepository<LiveLocation, String>{

}
