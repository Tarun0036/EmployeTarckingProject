package com.sira.employe_monitoring_system.entity;

public class LoginDetails {

	    private String loginTime;
	    private String logoutTime;
	    private String loginDate;
	    private String timeDifference;

	    public LoginDetails() {
	    	
	    }

	    public LoginDetails(String loginTime, String logoutTime, String loginDate, String timeDifference) {
	        this.loginTime = loginTime;
	        this.logoutTime = logoutTime;
	        this.loginDate = loginDate;
	        this.timeDifference = timeDifference;
	    }

	    // Getters and Setters
	    public String getLoginTime() {
	        return loginTime;
	    }

	    public void setLoginTime(String loginTime) {
	        this.loginTime = loginTime;
	    }

	    public String getLogoutTime() {
	        return logoutTime;
	    }

	    public void setLogoutTime(String logoutTime) {
	        this.logoutTime = logoutTime;
	    }

	    public String getLoginDate() {
	        return loginDate;
	    }

	    public void setLoginDate(String loginDate) {
	        this.loginDate = loginDate;
	    }

		public String getTimeDifference() {
			return timeDifference;
		}

		public void setTimeDifference(String timeDifference) {
			this.timeDifference = timeDifference;
		}

	  
		

		

}
