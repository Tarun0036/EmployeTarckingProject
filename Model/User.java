package com.example.SuperAdmin.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "/Admin")
public class User {
	@Id
	private String _id;
	private String name;
	private String email;
	private String password;
	private String role;
	private String empRole;
	private String employeId;
	private int adminId;
	private String Contact;
	private String noOfEmp;
	private boolean isDeleted;
	private String company;
	private String companyId;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getNoOfEmp() {
		return noOfEmp;
	}

	public void setNoOfEmp(String noOfEmp) {
		this.noOfEmp = noOfEmp;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompany_id(String companyId) {
		this.companyId = companyId;
	}

}
