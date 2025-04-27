package com.database.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database.entity.TrackDetails;

public interface ITrackDetailsDao extends JpaRepository<TrackDetails, Long> {

	Optional<List<TrackDetails>> findByUserUsername(String username);
	Optional<List<TrackDetails>> findByDriverUsername(String username);
}
