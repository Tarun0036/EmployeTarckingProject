package com.example.SuperAdmin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SuperAdmin.Model.Company;
import com.example.SuperAdmin.ServiceLayer.companyImplem;

@RestController
@RequestMapping("/Organizations")
public class companyController {
	@Autowired
	private companyImplem service;

	@PostMapping("/post")
	public Company createCompany(@RequestBody Company details) {
		Company company = service.createCompany(details);
		return company;
	}

	@GetMapping("/allOrganization")
	public List<Company> getAllOrganization() {
		List<Company> allOrganization = service.getAllOrganization();
		return allOrganization;

	}

	@GetMapping("/organization/{_id}")
	public Company getMethodName(@PathVariable String _id) {
		Company organizationBy_Id = service.getOrganizationBy_Id(_id);
		return organizationBy_Id;
	}

}