package com.sira.employe_monitoring_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.Organistion;
import com.sira.employe_monitoring_system.service.Organizationservice;

@RestController
@RequestMapping("/organization")
@CrossOrigin("http://localhost:3000")
public class OrganizationController {

	@Autowired
	private Organizationservice service;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/register")
	public Organistion registerOrganization(@RequestBody Organistion org) {
		
		return service.regiterOrganization(org);
	}
	
}
//class RegisterOrganistion
//{
//	
//	private String organization;
//	private String location;
//	private String country;
//	private String email;
//	private String contact;
//	private long noOfEmployes;
//	
//	public RegisterOrganistion()
//	{
//		
//	}
//
//	public String getOrganization() {
//		return organization;
//	}
//
//	public void setOrganization(String organization) {
//		this.organization = organization;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getContact() {
//		return contact;
//	}
//
//	public void setContact(String contact) {
//		this.contact = contact;
//	}
//
//	public long getNoOfEmployes() {
//		return noOfEmployes;
//	}
//
//	public void setNoOfEmployes(long noOfEmployes) {
//		this.noOfEmployes = noOfEmployes;
//	}	
//}