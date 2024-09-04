package com.example.SuperAdmin.ServiceLayer;

import java.util.List;
import java.util.Map;

import com.example.SuperAdmin.Model.AdminResponseDto;
import com.example.SuperAdmin.Model.EmployeeResponseDto;
import com.example.SuperAdmin.Model.User;

public interface UserService {
	// Admin
	Map<String, String> getDetailsByemail(String email);

	Map<String, String> updateAdminById(int adminId, User user);

	boolean softDeleteAdminById(int adminId);

	AdminResponseDto createAdmin(User user);

	EmployeeResponseDto createEmployee(User user);

	List<Map<String, String>> getAllAdmins(String companyId);

	// login
	User createUser(User user);

	boolean isValidRole(String role);

	User getUserByEmail(String email);

}
