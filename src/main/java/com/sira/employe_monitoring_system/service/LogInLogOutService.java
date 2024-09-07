package com.sira.employe_monitoring_system.service;

import com.sira.employe_monitoring_system.entity.LoginLogutDetails;

public interface LogInLogOutService {

	LoginLogutDetails login(String employeId);
	
	LoginLogutDetails logOut(String employeId);
	
	LoginLogutDetails getDetails(String employeId);
}
