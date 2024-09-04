package com.example.SuperAdmin.Model;

public class AdminResponseDto {

	private int adminId;
	private String name;
	private String email;
	private String password;
	private String company;

	public AdminResponseDto(int adminId, String name, String email, String password, String company) {
		this.adminId = adminId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.company = company;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public String company() {
		return company;
	}

	public void setNameOfOrganization(String company) {
		this.company = company;
	}

}
