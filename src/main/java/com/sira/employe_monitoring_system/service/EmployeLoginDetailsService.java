package com.sira.employe_monitoring_system.service;

import com.sira.employe_monitoring_system.entity.EmployeLoginDetails;

public interface EmployeLoginDetailsService {

	EmployeLoginDetails loginTime(String format);
	
	EmployeLoginDetails logOut(String empId);
	
}
