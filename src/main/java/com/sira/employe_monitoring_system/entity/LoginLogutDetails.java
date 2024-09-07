package com.sira.employe_monitoring_system.entity;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loginLogutDetails")
public class LoginLogutDetails {

	private String _id;
	private String employeId;
	private String name;
	private String loginTime;
	private String logutTime;
	private String date;
	
	private String totalLoggedTime;
	
	private Map<String, String> logs;
	
	public LoginLogutDetails()
	{
		
	}

	
	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getEmployeId() {
		return employeId;
	}

	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogutTime() {
		return logutTime;
	}

	public void setLogutTime(String logutTime) {
		this.logutTime = logutTime;
	}

	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTotalLoggedTime() {
		return totalLoggedTime;
	}

	public void setTotalLoggedTime(String totalLoggedTime) {
		this.totalLoggedTime = totalLoggedTime;
	}

	public Map<String, String> getLogs() {
		return logs;
	}

	public void setLogs(Map<String, String> logs) {
		this.logs = logs;
	}
	
	
}
