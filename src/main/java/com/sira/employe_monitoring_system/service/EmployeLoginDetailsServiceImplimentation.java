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

import com.sira.employe_monitoring_system.entity.EmployeLoginDetails;
import com.sira.employe_monitoring_system.repository.EmployeLoginDetailsRepository;

@Service
public class EmployeLoginDetailsServiceImplimentation implements EmployeLoginDetailsService {

    @Autowired
    private EmployeLoginDetailsRepository repo;

    @Override
    public EmployeLoginDetails loginTime(String empId) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String loginTime = now.format(timeFormat);

        EmployeLoginDetails employeLoginDetails = repo.findByEmployeId(empId);
        if (employeLoginDetails == null) {
            throw new RuntimeException("No Employee Present with this " + empId);
        }

        // Get the current date
        LocalDate today = LocalDate.now();

        // Check if the employee has logged in on the same day
        if (today.equals(employeLoginDetails.getLoginDate())) {
            // If the employee logs in again on the same day, clear the previous logout time
            employeLoginDetails.setLogutTime(null);
        }

        // Set the login time for the employee
        employeLoginDetails.setLoginTime(loginTime);

        // Store the current date as the login date
        employeLoginDetails.setLoginDate(today);

        repo.save(employeLoginDetails);
        return employeLoginDetails;
    }


    @Override
    public EmployeLoginDetails logOut(String empId) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String logoutTime = now.format(timeFormat);

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = today.format(dateFormat);

        String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        EmployeLoginDetails employeLoginDetails = repo.findByEmployeId(empId);
        if (employeLoginDetails == null) {
            throw new RuntimeException("No Employee Present with this employeeID " + empId);
        }

        LocalTime loginTime = LocalTime.parse(employeLoginDetails.getLoginTime(), timeFormat);
        LocalTime logoutTimeParsed = LocalTime.parse(logoutTime, timeFormat);

        if (today.equals(employeLoginDetails.getLoginDate())) {
            // Same day login and logout
            employeLoginDetails.setLogutTime(logoutTime);
        } else {
            // Different day login and logout (new entry)
            employeLoginDetails.setLoginDate(today); // Update to today's date
            employeLoginDetails.setLoginTime(logoutTime); // Set today's login time
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
        logs.put(date, hours + ":" + minutes + ":" + seconds);
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
}
