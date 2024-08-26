package com.sira.employe_monitoring_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project")
public class Project {

	private String _id;
	private String nameOfOrganization;
	private String email;
	private String password;
	private String role;
	private String empRole;
	private String employeId;
	private String adminId;
	private Integer noOfAdmins;
	private String contact;
	private String locationOfOrganization;
	private String noOfEmp;
	private String country;
	private boolean isDeleted;
	
	//private String name;
	//private String email;
	//private String mobile;
	//private String location;
	//private String country;
	public Project()
	{
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public String getEmployeId() {
		return employeId;
	}

	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public String getNoOfEmp() {
		return noOfEmp;
	}

	public void setNoOfEmp(String noOfEmp) {
		this.noOfEmp = noOfEmp;
	}

	
	
}
