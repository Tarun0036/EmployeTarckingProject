package com.sira.employe_monitoring_system.service;

import java.util.Optional;

import com.sira.employe_monitoring_system.entity.Admin;

public interface AdminService {

	Admin addAdmin(Admin admin);
	
	boolean adminLogin(String email, String password);
	
	Admin getAdminByEmail(String email);
}
