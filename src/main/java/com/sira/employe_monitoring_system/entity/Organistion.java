package com.sira.employe_monitoring_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organization")
public class Organistion {

	private String _id;
	private String nameOfOrganization;
	private String locationOfOrganization;
	private String country;
	private String email;
	private String contact;
	private String noOfEmp;
	
	public Organistion()
	{
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNameOfOrganization() {
		return nameOfOrganization;
	}

	public void setNameOfOrganization(String nameOfOrganization) {
		this.nameOfOrganization = nameOfOrganization;
	}

	public String getLocationOfOrganization() {
		return locationOfOrganization;
	}

	public void setLocationOfOrganization(String locationOfOrganization) {
		this.locationOfOrganization = locationOfOrganization;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getNumberOfEmployes() {
		return noOfEmp;
	}

	public void setNumberOfEmployes(String numberOfEmployes) {
		this.noOfEmp = numberOfEmployes;
	}
	
	
}
