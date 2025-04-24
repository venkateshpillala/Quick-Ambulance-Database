package com.database.entity;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ambulance {

	@Id
	@NotNull
	@Size(min=4,max=10)
	private String vehicleNumber;
	
	private String color;
	private Long yearOfModel;
	private String company;
	
	@Lob
	@NotNull
	private byte[] cbook;
	
	private Boolean status;
	
	@Transient
	private MultipartFile book;

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getYearOfModel() {
		return yearOfModel;
	}

	public void setYearOfModel(Long yearOfModel) {
		this.yearOfModel = yearOfModel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public byte[] getCbook() {
		return cbook;
	}

	public void setCbook(byte[] cbook) {
		this.cbook = cbook;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public MultipartFile getBook() {
		return book;
	}

	public void setBook(MultipartFile book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Ambulance [vehicleNumber=" + vehicleNumber + ", color=" + color + ", yearOfModel=" + yearOfModel
				+ ", company=" + company + "]";
	}
	
}
