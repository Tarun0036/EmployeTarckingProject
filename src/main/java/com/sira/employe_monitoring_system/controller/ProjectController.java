package com.sira.employe_monitoring_system.controller;



import java.util.List;
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
import com.sira.employe_monitoring_system.entity.Project;
import com.sira.employe_monitoring_system.service.ProjectService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService service;
	
	// Organization Registration
	@PostMapping("/organizationRegitration")
	public ResponseEntity<String> organisationRegistration(@RequestBody Project project) {
		Project organistaion = service.addOrganistaion(project);
		if (organistaion != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Organization added");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No Organization");
		}
	}
	
	//Edit Organization
	@PutMapping("/edit/{name}")
	public ResponseEntity<String> updateUser(@PathVariable("name") String username, @RequestBody Project userDto) {
	    boolean isUpdated = service.updateOrganization(username, userDto);
	    if (isUpdated) {
	        return ResponseEntity.ok("User updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	}
	
	//Delete Organization
	@PutMapping("/delete/{name}")
	public ResponseEntity<String> deleteOrganization(@PathVariable String name)
	{
		boolean organization = service.deleteOrganization(name);
		if(organization)
		{
			return ResponseEntity.ok("Deleted Successfully--!");
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't Delete");
		}
	}
	
	@GetMapping("/get/{name}")
	public Project getOrganizationByName(@PathVariable String name)
	{
		return service.getByName(name);
	}
	
	//Super User(Add)
	@PostMapping("/add/superuser")
	public ResponseEntity<String> addSuperUser(@RequestBody Project project)
	{
		Project superUser = service.addSuperUser(project);
		if(superUser != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Super USer added");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No Super User Added");
		}
	}
	
	//Add Admin
	@PostMapping("/reg/admin")
	public ResponseEntity<String> addAdmin(@RequestBody Project project)
	{
		Project superUser = service.addAdmin(project);
		if(superUser != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Super USer added");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No Super User Added");
		}
	}
	
	//add Employee
	@PostMapping("/add/emp")
	public ResponseEntity<String> addEmploye(@RequestBody Project project)
	{
		Project emploe = service.addEmploe(project);
		if(emploe != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Employe added");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No Employe Added");
		}
	}
	
	//All Organizations
	@GetMapping("/allOrganizations")
	public List<Project> getAllOrganizations()
	{
		List<Project> list = service.getAllOrganizations();
		if(list == null)
		{
			ResponseEntity.status(HttpStatus.CONFLICT).body("No Organizations Present");
		}
		return list;
	}
	
	//All Admins
	@GetMapping("/allAdmins")
	public List<Project> getAllAdmins()
	{
		List<Project> list = service.getAllAdmins();
		if(list == null)
		{
			ResponseEntity.status(HttpStatus.CONFLICT).body("No Organizations Present");
		}
		return list;
	}
	
	//All Employees
	@GetMapping("/allEmployes")
	public Map getAllEmployes()
	{
		Map<String, String> map = service.getAllEmployes();
		if(map == null)
		{
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("No EMployees are present");
		}
		return map;
	}
	
	//Edit Employees
	@PutMapping("/editEmploye/{empId}")
	public ResponseEntity<?> editEmploye(@PathVariable String empId, @RequestBody Project project)
	{
		Project employe = service.updateEmploye(empId, project);
		if(employe == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Employes Present");
		}
		return ResponseEntity.ok(employe);
	}
	
	//Login Validation
	@PostMapping("/loginValidation")
	public String loginValidation(@RequestBody LoginCred login)
	{
		String validation = service.loginValidation(login.getEmail(), login.getPassword(), login.getRole());
		return validation;
	}
	
	
//	@PostMapping("/loginValidation")
//	public Project loginValidation(@RequestBody LoginCred login)
//	{		
//		Project loginValidation = service.loginValidation(login.getEmail(), login.getPassword(), login.getRole());
//		if(loginValidation == null)
//		{
//			 ResponseEntity.status(HttpStatus.BAD_REQUEST).body("In-valid Credentials");
//		}
//		return  loginValidation;
//	}
}

class LoginCred
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
