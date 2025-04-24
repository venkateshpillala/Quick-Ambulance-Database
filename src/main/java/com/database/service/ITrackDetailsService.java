package com.database.service;

import java.util.List;

import com.database.dto.TrackDetailsDTO;
import com.database.entity.TrackDetails;

public interface ITrackDetailsService {

	public Long saveBookingDetails(TrackDetailsDTO dto);
	public List<TrackDetails> userHistory(String username);
}
