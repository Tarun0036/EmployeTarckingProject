package com.sira.employe_monitoring_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.SuperUser;
import com.sira.employe_monitoring_system.service.SuperUserService;

@RestController
@RequestMapping("/superuser")
public class SuperUserController {

	@Autowired
	private SuperUserService service;
	
	@PostMapping("/add")
	public ResponseEntity<String> addSuperUser(@RequestBody SuperUser sUser)
	{
		SuperUser superUser = service.addSuperUser(sUser); 
		if(superUser != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Super User added");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("No one added");
		}
	}
	
	@PostMapping("/login")
	public boolean checkCredentails(@RequestBody LoginCredentials cred)
	{
		return service.checkCredentials(cred.getEmail(), cred.getPassword(), cred.getRole());
	}
}

class LoginCredentials
{
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