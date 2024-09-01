package com.sira.employe_monitoring_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {

	private String _id;
	private String company;
	private String name;
	private String email;
	private String password;
	private String role;
	private String empRole;
	private String contact;
	private String employeId;
	private String adminId;
	private String companyId;
	private boolean isDeleted;
	
	public Users()
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	@Override
	public String toString() {
		return "Users [_id=" + _id + ", company=" + company + ", name=" + name + ", email=" + email + ", password="
				+ password + ", role=" + role + ", empRole=" + empRole + ", contact=" + contact + ", employeId="
				+ employeId + ", adminId=" + adminId + ", companyId=" + companyId + ", isDeleted=" + isDeleted + "]";
	}
	
	
}
