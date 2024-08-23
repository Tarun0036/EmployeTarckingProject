package com.sira.employe_monitoring_system.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin")
public class Admin {

	private String _id;
	private String email;
	private String password;
	
	public Admin()
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


	@Override
	public String toString() {
		return "Admin [_id=" + _id + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
