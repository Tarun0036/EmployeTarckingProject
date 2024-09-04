package com.example.SuperAdmin.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Company")
public class Company {
	@Id
	private String _id;
	private String company;
	private String email;
	private String Contact;
	private String locationOfOrganization;
	private String noOfEmp;
	private String country;
	private boolean isDeleted;
	private int noOfAdmins;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String nameOfOrganization) {
		this.company = nameOfOrganization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
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

	public int getNoOfAdmins() {
		return noOfAdmins;
	}

	public void setNoOfAdmins(int noOfAdmins) {
		this.noOfAdmins = noOfAdmins;
	}

}
