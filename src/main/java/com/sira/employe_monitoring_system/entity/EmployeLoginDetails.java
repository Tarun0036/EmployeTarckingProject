package com.sira.employe_monitoring_system.entity;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employe-login-details")
public class EmployeLoginDetails {

	private String _id;
	private String employeId;
	private String loginTime;
	private String logutTime;
	private LocalDate loginDate;
	
	private Map<String, String> logs;
	
	public EmployeLoginDetails()
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
	
	

	public LocalDate getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}

	public Map getLogs() {
		return logs;
	}

	public void setLogs(Map logs) {
		this.logs = logs;
	}
	
	
}
