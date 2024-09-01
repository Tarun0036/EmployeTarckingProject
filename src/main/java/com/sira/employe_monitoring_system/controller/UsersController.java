package com.sira.employe_monitoring_system.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.Users;
import com.sira.employe_monitoring_system.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService service;

	// Add Super User
	@PostMapping("/addSuperUser")
	public Users addSuperUser(@RequestBody Users user) {
		return service.addSuperUser(user);
	}

	// Admin Registration
	@PostMapping("/addAdmin")
	public Users addAdmin(@RequestBody Users user) {
		Users admin = service.addAdmin(user);
		if (admin == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO Admin Added");
		}
		return admin;
	}

	// Employee Registration
	@PostMapping("/addEmploye")
	public Users addEmploye(@RequestBody Users user) {
		Users employe = service.addEmploye(user);
		if (employe == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO Admin Added");
		}
		return employe;
	}

	// login validation
	@PostMapping("/loginValidation")
	public String loginValidation(@RequestBody LoginCredentials login) {
		String validation = service.loginValidation(login.getEmail(), login.getPassword(), login.getRole());
		return validation;
	}

	// All Employees In a Company
	@GetMapping("/employesInCompany/{role}/{company}")
	public List<Users> getAllEmployesInCompany(@PathVariable String role, @PathVariable String company)
	{
		return service.getAllEmployesInCompany(role, company);
	}
	
	@GetMapping("/allEmployes/{role}/{company}")
	public Map allEmployes(@PathVariable String role, @PathVariable String company) 
	{
		return service.getAllEmployes(role, company);
	}

	// Edit Employee
	@PutMapping("/editEmploye/{empId}")
	public ResponseEntity<Users> editEmploye(@PathVariable String empId, @RequestBody Users user) {
		Users editEmploye = service.editEmploye(empId, user);
		if (editEmploye != null) {
			return new ResponseEntity<>(editEmploye, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/deleteEmploye/{empId}")
	public ResponseEntity deleteEmploye(@PathVariable String empId) {
		boolean employeById = service.deleteEmployeById(empId);
		if (employeById) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Employe Deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't Delete");
		}
	}
	
	
	@GetMapping("/getAmdins/{company}")
	public Map getAdminInCompany(@PathVariable String company)
	{
		return service.getByCompanyName(company);
	}
	
	@GetMapping("/getAdminByCompanyId/{companyId}")
	public Map getAdminByCompanyId(@PathVariable String companyId)
	{
		return service.getAdminByCompanyId(companyId);
	}
	
	
	@PutMapping("/editAdmin/{adminId}")
	public Users editAdminByAdminId(@PathVariable String adminId, @RequestBody Users user)
	{
		return service.editAdminByAdminId(adminId, user);
	}
	
	@PutMapping("/deleteAdmin/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable String adminId)
	{
		boolean deleteAdminByAdminId = service.deleteAdminByAdminId(adminId);
		if(deleteAdminByAdminId)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Admin Deleted");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Can't Delete");
		}
	}
	
//	@GetMapping("/getCompany/{company}")
//	public List<Users> getCompany(@PathVariable String company)
//	{
//		return service.getCompany(company);
//	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<Users> validate(@RequestBody LoginCredentials cred)
	{
		 Users users = service.login(cred.getEmail(), cred.getPassword());
		if(users != null)
		{
			
			return new  ResponseEntity<>(users, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}

class LoginCredentials {
	private String email;
	private String password;
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}