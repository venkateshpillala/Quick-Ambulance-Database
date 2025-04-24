package com.database.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrackDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long trackId;
	
	private LocalDate bookDate;
	private LocalTime bookTime;
	private String pickup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicleNumber")
	private Ambulance ambulance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_name")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver")
	private Driver driver;
	
	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	public LocalTime getBookTime() {
		return bookTime;
	}

	public void setBookTime(LocalTime bookTime) {
		this.bookTime = bookTime;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public Ambulance getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "TrackDetails [trackId=" + trackId + ", date=" + bookDate + ", time=" + bookTime + ", pickup=" + pickup
				+ ", drop="  + ", ambulance=" + ambulance + ", user=" + user + ", driver=" + driver + "]";
	}
	
	
	
}
