package com.sira.employe_monitoring_system.entity;

import java.util.ArrayList;
import java.util.List;


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

    private List<LoginDetails> logs = new ArrayList<>(); // Update this to a list of log details

    public LoginLogutDetails()
    {
    	
    }

    public LoginLogutDetails(String _id, String employeId, String name, String loginTime, String logutTime, String date,
			String totalLoggedTime, List<LoginDetails> logs) {
		super();
		this._id = _id;
		this.employeId = employeId;
		this.name = name;
		this.loginTime = loginTime;
		this.logutTime = logutTime;
		this.date = date;
		this.totalLoggedTime = totalLoggedTime;
		this.logs = logs;
	}



	// Getters and Setters
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

    public List<LoginDetails> getLogs() {
        return logs;
    }

    public void setLogs(List<LoginDetails> logs) {
        this.logs = logs;
    }

}
