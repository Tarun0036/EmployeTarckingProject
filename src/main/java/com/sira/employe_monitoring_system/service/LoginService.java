package com.sira.employe_monitoring_system.service;

import com.sira.employe_monitoring_system.entity.Login;

public interface LoginService {

	Login addOne(Login login);
	
	Login validateCredentials(String email, String password, String role);
	
	Login getByEmail(String email);
}
