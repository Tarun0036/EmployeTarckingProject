package com.sira.employe_monitoring_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.LoginLogutDetails;
import com.sira.employe_monitoring_system.service.LogInLogOutService;

@RestController
@RequestMapping("/employe")
public class LoginLogoutController {

	@Autowired
	private LogInLogOutService service;
	
	@PutMapping("/logintTime/{employeId}")
	public ResponseEntity<LoginLogutDetails> login(@PathVariable String employeId)
	{
		LoginLogutDetails loginLogutDetails = service.login(employeId);
		if(loginLogutDetails != null)
		{
			return new ResponseEntity<LoginLogutDetails>(loginLogutDetails, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<LoginLogutDetails>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/logout/{employeId}")
	public ResponseEntity<LoginLogutDetails> logOut(@PathVariable String employeId)
	{
		LoginLogutDetails loginLogutDetails = service.logOut(employeId);
		if(loginLogutDetails != null)
		{
			return new ResponseEntity<LoginLogutDetails>(loginLogutDetails, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<LoginLogutDetails>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getEmpDetails/{employeId}")
	public ResponseEntity<LoginLogutDetails> getDetails(@PathVariable String employeId)
	{
		LoginLogutDetails loginLogutDetails = service.getDetails(employeId);
		if(loginLogutDetails != null)
		{
			return new ResponseEntity<LoginLogutDetails>(loginLogutDetails, HttpStatus.OK);
		}
		else
		{
			return  new ResponseEntity<LoginLogutDetails>(HttpStatus.NO_CONTENT);
		}
	}
}
