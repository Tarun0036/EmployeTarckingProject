package com.sira.employe_monitoring_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.EmployeLoginDetails;
import com.sira.employe_monitoring_system.service.EmployeLoginDetailsService;

@RestController
@RequestMapping("/employe")
public class EmployeLoginDetailsController {

	@Autowired
	private EmployeLoginDetailsService service;
	
	
	@PutMapping("/logintTime/{empId}")
	public EmployeLoginDetails loginTime(@PathVariable String empId)
	{
			EmployeLoginDetails employeLoginDetails = service.loginTime(empId);
			return employeLoginDetails;
	}
	
	@PutMapping("/logout/{empId}")
	public EmployeLoginDetails logut(@PathVariable String empId)
	{
		return service.logOut(empId);
	}
	
}
