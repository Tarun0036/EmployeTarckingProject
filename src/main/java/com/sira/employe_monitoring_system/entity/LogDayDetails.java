package com.sira.employe_monitoring_system.entity;

import java.util.List;

public class LogDayDetails {

	 private String date;
	    private List<LoginDetails> logs;

	    // Getters and setters
	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }

	    public List<LoginDetails> getLogs() {
	        return logs;
	    }

	    public void setLogs(List<LoginDetails> logs) {
	        this.logs = logs;
	    }
}
