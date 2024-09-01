package com.sira.employe_monitoring_system.service;

import java.util.List;
import java.util.Map;

import com.sira.employe_monitoring_system.entity.Users;

public interface UsersService {

	Users addSuperUser(Users user);

	Users addAdmin(Users user);

	Users addEmploye(Users user);

	Map getAllEmployes(String role, String name);
	
	List<Users> getAllEmployesInCompany(String role, String name);
	
	String loginValidation(String email, String password, String role);
	
	Users editEmploye(String employeId, Users getObject);
	
	boolean deleteEmployeById(String empId);
	
	Map getByCompanyName(String company);
	
	Map getAdminByCompanyId(String companyId);
	
	Users editAdminByAdminId(String adminId, Users user);
	
	boolean deleteAdminByAdminId(String adminId);
	
	//Map<String, String> login(String email, String password);
	//List<Users> getCompany(String company);
	
	
	Users login(String email, String password);

}
