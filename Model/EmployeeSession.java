package com.example.SuperAdmin.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class EmployeeSession {
	private Long id;

	private String employeeId;
	private LocalDate date;
	private LocalTime loginTime;
	private LocalTime logoutTime;
	private Duration workDuration;
	private Duration overtime;
	private boolean isAutoLoggedOut;

	private Duration standardWorkingHours = Duration.ofHours(8);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Duration getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(Duration workDuration) {
		this.workDuration = workDuration;
	}

	public Duration getOvertime() {
		return overtime;
	}

	public void setOvertime(Duration overtime) {
		this.overtime = overtime;
	}

	public boolean isAutoLoggedOut() {
		return isAutoLoggedOut;
	}

	public void setAutoLoggedOut(boolean isAutoLoggedOut) {
		this.isAutoLoggedOut = isAutoLoggedOut;
	}

	public Duration getStandardWorkingHours() {
		return standardWorkingHours;
	}

	public void setStandardWorkingHours(Duration standardWorkingHours) {
		this.standardWorkingHours = standardWorkingHours;
	}

}
