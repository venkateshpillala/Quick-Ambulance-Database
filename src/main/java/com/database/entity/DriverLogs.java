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
public class DriverLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logid; 
    
    private LocalDate loginDate; 
    private LocalTime login; 
    private LocalTime logout; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driverName")
    private Driver driver; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleNumber")
    private Ambulance ambulance;

	public Long getLogid() {
		return logid;
	}

	public void setLogid(Long loginid) {
		this.logid = loginid;
	}

	public LocalDate getLogDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}

	public LocalTime getLogin() {
		return login;
	}

	public void setLogin(LocalTime login) {
		this.login = login;
	}

	public LocalTime getLogout() {
		return logout;
	}

	public void setLogout(LocalTime logout) {
		this.logout = logout;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Ambulance getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	@Override
	public String toString() {
		return "DriverLogs [loginid=" + logid + ", date=" + loginDate + ", login=" + login + ", logout=" + logout
				+ ", driver=" + driver + ", ambulance=" + ambulance + "]";
	} 

    
    
}
