package com.sira.employe_monitoring_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.Company;
import com.sira.employe_monitoring_system.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/project")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@PostMapping("/organizationRegitration")
	public ResponseEntity<String> addOrganization(@RequestBody Company company)
	{
		Company organistaion = service.addOrganistaion(company);
		if(organistaion != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Organization added");
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No Organization");
		}
	}
	
	@PutMapping("/edit/{name}")
	public Company editCompany(@PathVariable String name, @RequestBody Company company)
	{
		return service.editCompany(name, company);
	}
	
	@PutMapping("/delete/{name}")
	public ResponseEntity<String> deleteCompany(@PathVariable String name)
	{
		boolean company = service.deleteCompany(name);
		if(company)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted successfully");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't Delete..");
		}
	}
	
	@GetMapping("/allOrganizations")
	public Map getAllCompanies()
	{
		Map map = service.getAllCompanies();
		if(map == null)
		{
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Copmanies present");
		}
		return map;
	}
	
	@GetMapping("/getByName/{name}")
	public Company getCompany(@PathVariable String name)
	{
		return service.getCompanyByName(name);
	}
}
