package com.sira.employe_monitoring_system.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sira.employe_monitoring_system.entity.Company;
import com.sira.employe_monitoring_system.entity.LoginLogutDetails;
import com.sira.employe_monitoring_system.entity.Users;
import com.sira.employe_monitoring_system.exception.InvalidRole;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.exception.OrganizationNameAlreadyExistsException;
import com.sira.employe_monitoring_system.repository.CompanyRepository;
import com.sira.employe_monitoring_system.repository.LoginLogutRepository;
import com.sira.employe_monitoring_system.repository.UsersRepository;

@Service
public class UsersServiceImplimentation implements UsersService {

	@Autowired
	private UsersRepository repo;

	@Autowired
	private LoginLogutRepository employeLoginRepo;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private BCryptPasswordEncoder encode;

	@Override
	public Users addSuperUser(Users user) {
		user.setPassword(encode.encode(user.getPassword()));
		user.setActive(true);
		return repo.save(user);
	}

	@Override
	@Transactional
	public Users addEmploye(Users user) {
		if (user.getContact().isEmpty() || user.getContact() == null || user.getEmail().isEmpty()
				|| user.getEmail() == null || user.getPassword().isEmpty() || user.getPassword() == null
				|| user.getEmpRole() == null || user.getEmpRole().isEmpty() || user == null) {
			throw new MandatoryFeildExcetion("All Feilds are Mandatory");
		}
		if (repo.existsByEmail(user.getEmail()) && user.isDeleted() == false) {
			throw new OrganizationNameAlreadyExistsException("This Maill is alredy Regitered.!");
		}
		String empId = "Emp";
		Random random = new Random();
		int adId = random.nextInt(1000);
		empId = empId + adId;

		LoginLogutDetails employe = new LoginLogutDetails();
		employe.setEmployeId(empId);
		employe.setName(user.getName());
		employeLoginRepo.save(employe);

		user.setEmployeId(empId);
		if (repo.existsByEmployeId(empId)) {
			throw new OrganizationNameAlreadyExistsException("This admin Id is already exists..!");
		}

		user.setPassword(encode.encode(user.getPassword()));
		user.setRole("User");
		return repo.save(user);
	}

	@Override
	public Users addAdmin(Users user) {
		if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword().isEmpty()
				|| user.getEmail() == null) {
			throw new OrganizationNameAlreadyExistsException("All Feilds are Mandatory");
		}
		if (repo.existsByEmail(user.getEmail()) && user.isDeleted() == false) {
			throw new OrganizationNameAlreadyExistsException("This Maill is alredy Regitered.!");
		}
		user.setRole("Admin");
		String adminId = "Admin";
		Random random = new Random();
		int adId = random.nextInt(1000);
		adminId = adminId + adId;
		if (repo.existsByAdminId(adminId)) {
			throw new OrganizationNameAlreadyExistsException("This admin Id is already exists..!");
		}
		user.setAdminId(adminId);
		user.setPassword(encode.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public String loginValidation(String email, String password, String role) {
		Users user = repo.findByEmail(email); // Fetch user by email

		if (user != null) {

			// Validate password
			if (encode.matches(password, user.getPassword())) {

				// Validate role
				if (user.getRole().equalsIgnoreCase(role)) {

					// Check if user is already active
					if (user.isActive()) {
						throw new OrganizationNameAlreadyExistsException("User is already active.");
					}

					// Mark user as active and save changes
					user.setActive(true);
					repo.save(user);

					// Return a message based on the role
					switch (role.toUpperCase()) {
					case "SUPER USER":
						return "Super User";
					case "ADMIN":
						return "Admin";
					case "USER":
						return "Employee";
					default:
						throw new InvalidRole("Invalid Role: " + role);
					}
				} else {
					throw new InvalidRole("Invalid Role: " + role);
				}
			} else {
				return "Invalid Password";
			}
		} else {
			return "Invalid Email";
		}
	}

	// All Employees in company
	@Override
	public List<Users> getAllEmployesInCompany(String role, String name) {

		return repo.findByRoleAndCompany(role, name);
	}

	// Edit Company
	@Override
	public Users editEmploye(String employeId, Users getObject) {
		Optional<Users> optional = repo.findById(employeId);
		if (optional.isEmpty()) {
			return null;
		}
		Users users = optional.get();
		users.setEmployeId(getObject.getEmployeId());
		users.setCompany(getObject.getCompany());
		users.setContact(getObject.getContact());
		users.setEmail(getObject.getEmail());

//		if (repo.existsByEmail(users.getEmail()) && users.isDeleted()==false) {
//			throw new OrganizationNameAlreadyExistsException("This Maill is alredy Regitered.!");
//		}

		LoginLogutDetails employeLoginDetails = new LoginLogutDetails();
		employeLoginDetails.setEmployeId(users.getEmployeId());
		employeLoginRepo.save(employeLoginDetails);
		users.setPassword(getObject.getPassword());
		users.setName(getObject.getName());
		users.setEmpRole(getObject.getEmpRole());
		return repo.save(users);
	}

	// Delete Company
	@Override
	public boolean deleteEmployeById(String empId) {
		Optional<Users> optional = repo.findById(empId);
		if (optional.isPresent()) {
			Users users = optional.get();
			users.setDeleted(true);
			repo.save(users);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Map<String, String>> getAllEmployes(String role, String name) {

		List<Users> list = repo.findByRoleAndCompany(role, name);

		if (list.size() != 0) {
			Map<String, Map<String, String>> map = new HashMap<>();
			Iterator<Users> iterator = list.iterator();

			while (iterator.hasNext()) {
				Users user = iterator.next();
				if (!user.isDeleted()) {
					HashMap<String, String> map1 = new HashMap<>();
					map1.put("_id", user.get_id());
					map1.put("employeId", user.getEmployeId());
					map1.put("company", user.getCompany());
					map1.put("email", user.getEmail());
					map1.put("empRole", user.getRole());
					map1.put("contact", user.getContact());
					map.put(user.getEmployeId(), map1);
				}
			}

			return map; // Moved outside the loop
		}

		return null;
	}

	@Override
	public Map<String, Map<String, String>> getByCompanyName(String company) {

		List<Users> list = repo.findByCompany(company);
		if (list.size() != 0) {
			Map<String, Map<String, String>> map = new HashMap<>();
			Iterator<Users> iterator = list.iterator();

			while (iterator.hasNext()) {
				Users user = iterator.next();
				if (!user.isDeleted() && user.getRole().equalsIgnoreCase("Admin")) {
					HashMap<String, String> map1 = new HashMap<>();
					map1.put("_id", user.get_id());
					map1.put("adminId", user.getAdminId());
					map1.put("name", user.getName());
					map1.put("email", user.getEmail());
					// map1.put("password", user.getPassword());
					map1.put("company", user.getCompany());

					map.put(user.getAdminId(), map1);
				}
			}

			return map;
		}
		return null;
	}

	@Override
	public List<Users> getAdminByCompanyId(String companyId) {

		Optional<Company> optional = companyRepo.findById(companyId);
		boolean deleted = optional.get().isDeleted();
		if (deleted == false) {
			List<Users> list = repo.findByCompanyIdAndIsDeletedFalse(companyId);
			if (list.size() != 0) {
				return list;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Users editAdminByAdminId(String adminId, Users getByUi) {

		Optional<Users> optional = repo.findById(adminId);
		if (optional.isEmpty()) {
			throw new RuntimeException("No Admin is Present with this Id : " + adminId);
		}
		Users users = optional.get();
		users.setAdminId(getByUi.getAdminId());
		users.setName(getByUi.getName());
		users.setEmail(getByUi.getEmail());
		users.setPassword(getByUi.getPassword());
		repo.save(users);
		return users;
	}

	@Override
	public boolean deleteAdminByAdminId(String adminId) {
		Optional<Users> optional = repo.findById(adminId);
		if (optional.isEmpty()) {
			return false;
		}
		Users users = optional.get();
		users.setDeleted(true);
		repo.save(users);
		return true;
	}

	@Override
	public Users login(String email, String password) {
		Users users = repo.findByEmail(email);
	    
	    // Check if the user exists
	    if (users != null) {
	        // If the user is a SUPER USER, skip company deletion check
	        if (users.getRole().equalsIgnoreCase("SUPER USER")) {
	            if (encode.matches(password, users.getPassword())) {
	                if (users.isActive()) {
	                    throw new OrganizationNameAlreadyExistsException("User Already in Active");
	                }
	                users.setActive(true);
	                repo.save(users);
	                return users;
	            } else {
	                return null; // Invalid password
	            }
	        } else {
	            // Fetch the user's company details
	            Company company = companyRepo.findByCompany(users.getCompany());

	            // Proceed with checks for non-super users
	            if (users.isDeleted() == false && company != null && company.isDeleted() == false) {
	                if (encode.matches(password, users.getPassword())) {
	                    if (users.isActive()) {
	                        throw new OrganizationNameAlreadyExistsException("User Already in Active");
	                    }
	                    users.setActive(true);
	                    repo.save(users);
	                    return users;
	                } else {
	                    return null; // Invalid password
	                }
	            }
	        }
	    }
	    
	    // If any validation fails, return null or throw an appropriate exception
	    return null;
	}

	@Override
	public List<Users> getAllUsers() {
		 List<Users> usersList = repo.findAll();  // Fetch all users
		    List<Users> filteredUsers = new ArrayList<>();

		    // Iterate through the list of users
		    for (Users user : usersList) {
		        String companyId = user.getCompanyId();  // Assuming each user has a reference to the company
		        Optional<Company> optional = companyRepo.findById(companyId);  // Fetch company details by companyId
		        Company company = optional.get();
		        if (company != null && !company.isDeleted()) {  // Check if the company is not marked as deleted
		            filteredUsers.add(user);  // Add the user to the filtered list if the company is active
		        }
		    }

		    return filteredUsers;
	}

	@Override
	public List<Users> getEmployesBasedOnAdminId(String amdinId) {
		List<Users> list = repo.findByRoleAndAdminIdAndIsDeletedFalse("User", amdinId);
		List<Users> list2 = repo.findByAdminId(amdinId);
		boolean deleted = list2.iterator().next().isDeleted();

		if (deleted == false) {
			if (list.size() != 0) {
				return list;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String logut(String empId) {
		Optional<Users> optional = repo.findById(empId);
		if (optional.isPresent()) {
			Users users = optional.get();
			if (users.isActive() == false) {
				return null;
			}
			users.setActive(false);
			repo.save(users);
			return "Logut Successfully";
		} else {
			return null;
		}
	}

	@Override
	public List<Users> getAllAdmins(String role) {
//		List<Users> list = repo.findByRoleAndIsDeletedFalse("Admin");
//		return list;
		
		 List<Users> admins = repo.findByRoleAndIsDeletedFalse("Admin");

		    // Filter admins based on their company's isDeleted status
		    List<Users> filteredAdmins = admins.stream()
		        .filter(admin -> {
		            // Fetch the company based on the companyId
		            Company company = companyRepo.findById(admin.getCompanyId()).orElse(null);
		            
		            // Include the admin only if the company exists and isDeleted is false
		            return company != null && !company.isDeleted();
		        })
		        .collect(Collectors.toList());

		    return filteredAdmins;
 	}

	@Override
	public List<Users> getAllEmployes(String role) {
		//return repo.findByRoleAndIsDeletedFalse("User");
		 List<String> activeCompanies = companyRepo.findByIsDeletedFalse()
                 .stream()
                 .map(Company::getCompany)
                 .collect(Collectors.toList());

// Fetch all users where role is 'User' and company is in the activeCompanies list
		 return repo.findByRoleAndCompanyInAndIsDeletedFalse("User", activeCompanies);
	}

//	public Map<String, String> login(String email, String password)
//	{
//		Users users = repo.findByEmail(email);
//		if(users != null)
//		{
//			if(encode.matches(password, users.getPassword()))
//			{
//				Map<String, String> map = new HashMap();
//				map.put("email", users.getEmail());
//				map.put("role", users.getRole());
//				map.put("adminId", users.getAdminId());
//				map.put("employeId", users.getEmployeId());
//				map.put("companyId", password);
//				map.put("_id", users.get_id());
//				
//				return map;
//			}
//			else
//			{
//				return null;
//			}
//		}
//		else
//		{
//			return null;
//		}
//	}
}
