package com.example.SuperAdmin.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SuperAdmin.Exceptionhandling.EmpAlreadyExistsException;
import com.example.SuperAdmin.Model.AdminResponseDto;
import com.example.SuperAdmin.Model.EmployeeResponseDto;
import com.example.SuperAdmin.Model.User;
import com.example.SuperAdmin.ServiceLayer.UserServiceImpl;
import com.sira.clockinout.Entity.UserDTO;
import com.sira.clockinout.ExceptionHandling.InvalidUserDataException;
import com.sira.clockinout.ExceptionHandling.UserAlreadyExistsException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/post")
	public ResponseEntity<?> createAdmin(@RequestBody User details) {
		try {
			AdminResponseDto response = service.createAdmin(details);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleAdminAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleException(RuntimeException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/admins/{companyId}")
	public List<Map<String, String>> getAdminsBycompanyId(@PathVariable String companyId) {
		return service.getAllAdmins(companyId);
	}

	@GetMapping("/get/{email}")
	public ResponseEntity<Map<String, String>> getByEmail(@PathVariable String email) {
		Map<String, String> map = service.getDetailsByemail(email);

		// Check if the map is null or empty
		if (map == null || map.isEmpty()) {
			// Return a 404 Not Found response with a message
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("message", "No Admins Present with this email."));
		}

		// Return 200 OK with the map if details are found
		return ResponseEntity.ok(map);
	}

	@PutMapping("/update/{adminId}")
	public Map<String, String> updateAdminById(@PathVariable int adminId, @RequestBody User user) {
		return service.updateAdminById(adminId, user);
	}

	@PutMapping("/delete/{adminId}")
	public ResponseEntity<String> softDeleteAdminById(@PathVariable int adminId) {
		boolean deleteAdminById = service.softDeleteAdminById(adminId);
		if (!deleteAdminById) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Record deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Deleted");
		}
	}

	// employeecontroller

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody User details) {
		try {
			EmployeeResponseDto response = service.createEmployee(details);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (EmpAlreadyExistsException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@ExceptionHandler(EmpAlreadyExistsException.class)
	public ResponseEntity<String> handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	/// logincontroller
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		String role = userDTO.getRole().toUpperCase();

		if (!role.equals("SUPER_ADMIN") && !role.equals("ADMIN") && !role.equals("EMPLOYEE")) {
			return new ResponseEntity<>("Invalid role provided", HttpStatus.BAD_REQUEST);
		}

		String email = userDTO.getEmail();
		String password = userDTO.getPassword();

		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			return new ResponseEntity<>("Email and password must be provided", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);

		try {
			service.createUser(user);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (InvalidUserDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(role + " created successfully", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
		String email = userDTO.getEmail();
		String password = userDTO.getPassword();
		String role = userDTO.getRole();

		if (email == null || email.isEmpty() || password == null || password.isEmpty() || role == null
				|| role.isEmpty()) {
			return new ResponseEntity<>("Email, password, and role must be provided", HttpStatus.BAD_REQUEST);
		}

		User user = service.getUserByEmail(email);

		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
		}

		if (!user.getRole().equalsIgnoreCase(role)) {
			return new ResponseEntity<>("Role mismatch", HttpStatus.UNAUTHORIZED);
		}

		if (!service.isValidRole(user.getRole())) {
			return new ResponseEntity<>("Invalid role for user", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>("Login successful", HttpStatus.OK);
	}

}