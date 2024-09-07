package com.sira.employe_monitoring_system.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.LoginLogutDetails;
import com.sira.employe_monitoring_system.repository.LoginLogutRepository;

@Service
public class LogInLogOutServiceImplimentation implements LogInLogOutService {

	@Autowired
	private LoginLogutRepository repo;
	@Override
	public LoginLogutDetails login(String employeId) {
	    LocalTime now = LocalTime.now();
	    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
	    String loginTime = now.format(timeFormat);

	    LoginLogutDetails employeLoginDetails = repo.findByEmployeId(employeId);
	    if (employeLoginDetails == null) {
	        throw new RuntimeException("No Employee Present with this " + employeId);
	    }

	    // Get the current date
	    LocalDate today = LocalDate.now();
	    
	    // Format the date to dd/MM/yyyy
	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String formattedDate = today.format(dateFormat);

	    // Check if the employee has logged in on the same day
	    if (formattedDate.equals(employeLoginDetails.getDate())) {
	        // If the employee logs in again on the same day, clear the previous logout time
	        employeLoginDetails.setLogutTime(null);
	    }

	    // Set the login time for the employee
	    employeLoginDetails.setLoginTime(loginTime);

	    // Store the current date as the login date (formatted as dd/MM/yyyy)
	    employeLoginDetails.setDate(formattedDate);

	    repo.save(employeLoginDetails);
	    return employeLoginDetails;
	}
	
	
	
	@Override
	public LoginLogutDetails logOut(String employeId) {
		    LocalTime now = LocalTime.now();
		    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
		    String logoutTime = now.format(timeFormat);

		    // Get the current date and format it as a string
		    LocalDate today = LocalDate.now();
		    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String formattedDate = today.format(dateFormat);

		    String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		    // Fetch the employee login details by employee ID
		    LoginLogutDetails employeLoginDetails = repo.findByEmployeId(employeId);
		    if (employeLoginDetails == null) {
		        throw new RuntimeException("No Employee Present with this employeeID: " + employeId);
		    }

		    // Parse login time from the stored string
		    LocalTime loginTime = LocalTime.parse(employeLoginDetails.getLoginTime(), timeFormat);
		    LocalTime logoutTimeParsed = LocalTime.parse(logoutTime, timeFormat);

		    // Compare using the formatted date string instead of LocalDate
		    if (formattedDate.equals(employeLoginDetails.getDate())) {
		        // Same day login and logout
		        employeLoginDetails.setLogutTime(logoutTime);
		    } else {
		        // Different day login and logout (new entry)
		        employeLoginDetails.setDate(formattedDate); // Update with formatted date string
		        employeLoginDetails.setLoginTime(logoutTime); // Set today's login time as logout time
		        employeLoginDetails.setLogutTime(null); // Clear previous logout time
		    }

		    // Calculate the total login time in seconds
		    long totalSeconds = calculateDuration(loginTime, logoutTimeParsed);

		    // Convert total seconds to hours, minutes, and seconds
		    long hours = totalSeconds / 3600;
		    long minutes = (totalSeconds % 3600) / 60;
		    long seconds = totalSeconds % 60;

		    // Update logs with the calculated duration
		    Map<String, String> logs = employeLoginDetails.getLogs();
		    if (logs == null) {
		        logs = new HashMap<>();
		    }
		    logs.put(formattedDate, hours + ":" + minutes + ":" + seconds);
		    employeLoginDetails.setTotalLoggedTime(hours + ":" + minutes + ":" + seconds);
		    employeLoginDetails.setLogs(logs);

		    return repo.save(employeLoginDetails);
		}

	 private static long calculateDuration(LocalTime loginTime, LocalTime logoutTime) {
	        // If logout time is before login time, add 24 hours to logout time
	        if (logoutTime.isBefore(loginTime)) {
	            logoutTime = logoutTime.plusHours(24);
	        }

	        // Calculate the duration in seconds
	        return ChronoUnit.SECONDS.between(loginTime, logoutTime);
	    }



	@Override
	public LoginLogutDetails getDetails(String employeId) {
		LoginLogutDetails loginLogutDetails = repo.findByEmployeId(employeId);
		if(loginLogutDetails != null)
		{
			return loginLogutDetails;
		}
		else
		{
			return null;
		}
	}
}
