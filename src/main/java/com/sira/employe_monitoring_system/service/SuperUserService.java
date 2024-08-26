package com.sira.employe_monitoring_system.service;

import com.sira.employe_monitoring_system.entity.SuperUser;

public interface SuperUserService {

	SuperUser addSuperUser(SuperUser superUser);
	
	boolean checkCredentials(String email, String password, String role);
}
