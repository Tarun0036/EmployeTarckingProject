package com.example.SuperAdmin.ServiceLayer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SuperAdmin.Exceptionhandling.EmpAlreadyExistsException;
import com.example.SuperAdmin.Exceptionhandling.MissingFieldException;
import com.example.SuperAdmin.Model.AdminResponseDto;
import com.example.SuperAdmin.Model.EmployeeResponseDto;
import com.example.SuperAdmin.Model.User;
import com.example.SuperAdmin.REpository.UserRepository;
import com.sira.clockinout.ExceptionHandling.InvalidUserDataException;
import com.sira.clockinout.ExceptionHandling.UserAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	Map<String, String> map = new HashMap();

	@Override
	public Map<String, String> getDetailsByemail(String email) {
		List<User> list = repo.findByemail(email);
		if (list.size() > 0) {
			Iterator<User> iterator = list.iterator();
			while (iterator.hasNext()) {
				User details = iterator.next();

				map.put("Name ", details.getName());
				map.put("Email", details.getEmail());
				map.put("AdminId", String.valueOf(details.getAdminId()));
				map.put("role", details.getRole());
				map.put("company", details.getCompany());
			}
			return map;
		} else {
			return null;
		}
	}

	@Override
	public Map<String, String> updateAdminById(int adminId, User user) {

		Optional<User> existingAdmin = repo.findByadminId(adminId).stream().findFirst();

		if (existingAdmin.isPresent()) {
			User admin = existingAdmin.get();

			admin.setName(user.getName());
			admin.setPassword(user.getPassword());
			admin.setEmail(user.getEmail());
			admin.setCompany(user.getCompany());

			User updatedAdmin = repo.save(admin);

			Map<String, String> response = new HashMap<>();
			response.put("Name", updatedAdmin.getName());
			response.put("Email", updatedAdmin.getEmail());
			response.put("Company", updatedAdmin.getCompany());
			// Convert adminId from int to String before putting it in the response map
			response.put("adminID", String.valueOf(updatedAdmin.getAdminId()));
			response.put("password", updatedAdmin.getPassword());

			return response;

		} else {
			throw new RuntimeException("Admin not found");
		}
	}

	@Override
	public boolean softDeleteAdminById(int adminId) {

		Optional<User> admin = repo.findByadminId(adminId).stream().findFirst();
		if (admin.isPresent()) {
			User user = admin.get();
			user.setDeleted(true);
			repo.save(user);
		} else {
			throw new RuntimeException("Admin not found");
		}
		return false;
	}

	@Override
	public AdminResponseDto createAdmin(User details) {

		if (repo.existsByEmail(details.getEmail())) {
			throw new UserAlreadyExistsException("Admin already exists with this email.");
		}

		// Check if an admin with the same adminId already exists
		if (repo.existsByAdminId(details.getAdminId())) {
			throw new UserAlreadyExistsException("Admin already exists with this ID.");
		}
		SecureRandom secureRandom = new SecureRandom();
		int generatedAdminId = secureRandom.nextInt(10000);
		if (details.getName() == null || details.getName().isEmpty() || details.getEmail() == null
				|| details.getEmail().isEmpty() || details.getPassword() == null || details.getPassword().isEmpty()
				|| details.getCompany() == null || details.getCompany().isEmpty()) {
			throw new MissingFieldException("All fields  are mandatory.");
		}
		User adminDetails = new User();
		adminDetails.set_id(details.get_id());

		adminDetails.setAdminId(generatedAdminId);
		adminDetails.setName(details.getName());
		adminDetails.setEmail(details.getEmail());
		adminDetails.setPassword(passwordEncoder.encode(details.getPassword()));
		adminDetails.setCompany(details.getCompany());
		adminDetails.setRole("Admin");

		User savedAdmin = repo.save(adminDetails);

		return new AdminResponseDto(savedAdmin.getAdminId(), savedAdmin.getName(), savedAdmin.getEmail(),
				savedAdmin.getPassword(), savedAdmin.getCompany());
	}

	@Override
	public List<Map<String, String>> getAllAdmins(String companyId) {
		List<User> list = repo.findByCompanyId(companyId);

		List<Map<String, String>> adminsList = new ArrayList<>();

		for (User details : list) {
			if (!details.isDeleted()) {
				Map<String, String> adminDetailsMap = new HashMap<>();
				adminDetailsMap.put("id", details.get_id());
				adminDetailsMap.put("Name", details.getName());
				adminDetailsMap.put("Email", details.getEmail());

				adminDetailsMap.put("AdminId", String.valueOf(details.getAdminId()));
				adminDetailsMap.put("CompanyId", details.getCompanyId());
				adminsList.add(adminDetailsMap);
			}
		}

		return adminsList;
	}

//employee
	@Override
	public EmployeeResponseDto createEmployee(User user) {
		if (repo.existsByEmail(user.getEmail())) {

			throw new EmpAlreadyExistsException("Employee already exists with this email.");
		}

		if (repo.existsByAdminId(user.getEmployeId())) {
			throw new EmpAlreadyExistsException("Employee already exists with this ID.");
		}
		if (user.getName() == null || user.getName().isEmpty() || user.getEmail() == null || user.getEmail().isEmpty()
				|| user.getPassword() == null || user.getPassword().isEmpty() || user.getCompany() == null
				|| user.getCompany().isEmpty()) {
			throw new MissingFieldException("All fields  are mandatory.");
		}
		User empdetails = new User();
		empdetails.set_id(user.get_id());
		empdetails.setName(user.getName());
		empdetails.setEmail(user.getEmail());
		empdetails.setPassword(passwordEncoder.encode(user.getPassword()));
		empdetails.setCompany(user.getCompany());
		empdetails.setContact(user.getContact());
		empdetails.setEmployeId(user.getEmployeId());
		empdetails.setEmpRole(user.getEmpRole());
		empdetails.setRole("employee");

		User savedempdetails = repo.save(empdetails);

		return new EmployeeResponseDto(savedempdetails.get_id(), savedempdetails.getName(), savedempdetails.getEmail(),
				savedempdetails.getCompany(), savedempdetails.getContact(), savedempdetails.getEmployeId(),
				savedempdetails.getEmpRole());
	}
	// login

	@Override
	public User createUser(User user) {
		if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null
				|| user.getPassword().isEmpty() || user.getRole() == null || user.getRole().isEmpty()) {
			throw new InvalidUserDataException("All fields are mandatory.");
		}

		if (repo.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User already exists with this email.");
		}

		if (user.getRole().equalsIgnoreCase("SUPER_ADMIN") && isSuperAdminExists()) {
			throw new InvalidUserDataException("Super Admin already exists.");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return repo.save(user);
	}

	private boolean isSuperAdminExists() {
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public boolean isValidRole(String role) {
		return role.equalsIgnoreCase("SUPER_ADMIN") || role.equalsIgnoreCase("ADMIN")
				|| role.equalsIgnoreCase("EMPLOYEE");
	}

}
