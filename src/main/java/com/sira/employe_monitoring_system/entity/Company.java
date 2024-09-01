package com.sira.employe_monitoring_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
public class Company {
	
	private String _id;
	private String company;
	private String email;
	private Integer noOfAdmins;
	private String contact;
	private String locationOfOrganization;
	private String noOfEmp;
	private String country;
	
	private boolean isDeleted;
	
	public Company()
	{
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNoOfAdmins() {
		return noOfAdmins;
	}

	public void setNoOfAdmins(Integer noOfAdmins) {
		this.noOfAdmins = noOfAdmins;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLocationOfOrganization() {
		return locationOfOrganization;
	}

	public void setLocationOfOrganization(String locationOfOrganization) {
		this.locationOfOrganization = locationOfOrganization;
	}

	public String getNoOfEmp() {
		return noOfEmp;
	}

	public void setNoOfEmp(String noOfEmp) {
		this.noOfEmp = noOfEmp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
