package com.sira.employe_monitoring_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sira.employe_monitoring_system.entity.Admin;
import com.sira.employe_monitoring_system.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping("/add")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
		Admin addAdmin = service.addAdmin(admin);
		if (addAdmin != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Admin Added");
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Admin Added");
		}
	}

	@PostMapping("/login")
    public boolean adminLogin(@RequestBody AmdminCredentials credentials) {
        return service.adminLogin(credentials.getEmail(), credentials.getPassword());
    }

	@GetMapping("/get/{email}")
	public Admin getAdmin(@PathVariable String email) {
		return service.getAdminByEmail(email);
	}
}

class AmdminCredentials {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
