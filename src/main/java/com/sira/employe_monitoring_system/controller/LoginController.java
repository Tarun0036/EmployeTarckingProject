package com.sira.employe_monitoring_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.Login;
import com.sira.employe_monitoring_system.service.LoginService;

@RestController
@RequestMapping("/loginpage")
public class LoginController {

	@Autowired
	private LoginService service;
	
	@PostMapping("/add")
	public Login addOne(@RequestBody Login login)
	{
		return service.addOne(login);
	}
	
	@GetMapping("/getByMail/{email}")
	public Login getByEmail(@PathVariable String email)
	{
		return service.getByEmail(email);	
	}
	
	@PostMapping("/validate")
	public ResponseEntity<String> validateCredentials(@RequestBody LoginCredent validate)
	{
		Login login = service.validateCredentials(validate.getEmail(), validate.getPassword(), validate.getRole());
		if(login == null || login.isDeleted()==true)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("In-valid Credentials");
		}
		return  new ResponseEntity<>(HttpStatus.CREATED);
	}
}
class LoginCredent
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