package com.sira.employe_monitoring_system.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.Project;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.exception.OrganizationNameAlreadyExistsException;
import com.sira.employe_monitoring_system.repository.ProjectRepository;

@Service
public class ProjectServiceImplimentation implements ProjectService {

	@Autowired
	private ProjectRepository repo;

	@Autowired
	private BCryptPasswordEncoder encode;

	// Organization Registration
	@Override
	public Project addOrganistaion(Project project) {
		if (project.getName().isEmpty() || project.getEmail().isEmpty() || project.getLocation().isEmpty()
				|| project.getCountry().isEmpty() || project.getMobile().isEmpty() || project.getName() == null
				|| project.getEmail() == null || project.getLocation() == null || project.getLocation() == null
				|| project.getMobile() == null || project == null || project.getNoOfEmps().isEmpty()||project.getNoOfEmps()==null) {
			throw new MandatoryFeildExcetion("All Feilds are Mandatory");
		}
		if(repo.existsByemail(project.getEmail()))
		{
			throw new OrganizationNameAlreadyExistsException("This Organization Mail is already Existed");
		}
		project.setRole("company");
		return repo.save(project);
	}

	// Edit Organization
	@Override
	public boolean updateOrganization(String name, Project userDto) {
		Optional<Project> optional = repo.findByName(name);
		if (optional.get().isDeleted()) {
			return false;
		}

		if (optional.isPresent()) {
			Project user = optional.get();
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setMobile(userDto.getMobile());
			user.setLocation(userDto.getLocation());
			user.setCountry(userDto.getCountry());
			repo.save(user);
			return true;
		}
		return false;
	}

	// Delete Organization
	@Override
	public boolean deleteOrganization(String name) {

		Optional<Project> optional = repo.findByName(name);
		if (optional.get().isDeleted()) {
			return false;
		}
		if (optional.isPresent()) {
			Project project = optional.get();
			project.setDeleted(true);
			repo.save(project);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Project getByName(String name) {

		return repo.findByName(name).get();
	}

	// Add Super User
	@Override
	public Project addSuperUser(Project project) {
		if (project.getEmail().isEmpty() || project.getPassword().isEmpty() || project.getRole().isEmpty()
				|| project == null || project.getEmail() == null || project.getPassword() == null
				|| project.getRole() == null) {
			throw new MandatoryFeildExcetion("All Feilds Are Mandatory");
		}
		project.setPassword(encode.encode(project.getPassword()));
		return repo.save(project);
	}
	// SuperUser@123

	// Add Admin
	@Override
	public Project addAdmin(Project project) {
		if (project.getAdminId().isEmpty() || project.getAdminId() == null || project.getEmail().isEmpty()
				|| project == null || project.getEmail() == null || project.getPassword().isEmpty()
				|| project.getPassword() == null || project.getName().isEmpty() || project.getName() == null
				|| project.getRole() == null || project.getRole().isEmpty()) {
			throw new MandatoryFeildExcetion("All Feilds Are Mandatory");
		}
		if(repo.existsByemail(project.getEmail()))
		{
			throw new OrganizationNameAlreadyExistsException("This admin mail is already Registered");
		}
		project.setPassword(encode.encode(project.getPassword()));
		return repo.save(project);
	}
	// Admin@123

	@Override
	public Project addEmploe(Project project) {
		if(project.getName()==null||project.getEmail()==null||project.getPassword()==null
				||project.getMobile()==null||project.getEmployeId()==null||
				project.getEmpRole()==null)
		{
			throw new MandatoryFeildExcetion("Fill the fill feilds properly");
		}
		if(repo.existsByemail(project.getEmail()))
		{
			throw new OrganizationNameAlreadyExistsException("This  Mail is already Registered");
		}
			project.setPassword(encode.encode(project.getPassword()));
			project.setRole("USER");
		return repo.save(project);
	}

//	@Override
//	public Project loginValidation(String email, String password, String role) {
//		 Project project = repo.findByEmail(email);
//		 if(project != null)
//		 {
//			 if(encode.matches(password, project.getPassword()))
//			 {
//				 if(role.equalsIgnoreCase(project.getRole()))
//				 {
//					 return project;
//				 }
//				 else
//				 {
//					 return null;
//				 }
//			 }
//			 else
//			 {
//				 return null;
//			 }
//		 }
//		 else
//		 {
//			 return null;
//		 }
//	}
	
	

	//All Organizations
	@Override
	public List<Project> getAllOrganizations() {
		List<Project> list = repo.findByrole("company");
		if(list.size() == 0)
		{
			return null;
		}
		return list;
	}

	//All Admins
	@Override
	public List<Project> getAllAdmins() {
		List<Project> list = repo.findByrole("ADMIN");
		if(list.size() == 0)
		{
			return null;
		}
		return list;
	}

	//All Employees
	
	Map<String, String> employes = new HashMap();
	@Override
	public Map getAllEmployes() {
		List<Project> list = repo.findByrole("USER");
		if(list.size() > 0)
		{
			Iterator<Project> iterator = list.iterator();
			while(iterator.hasNext())
			{
				Project next = iterator.next();
				employes.put("name", next.getName());
				employes.put("Employe Id", next.getEmployeId());
				employes.put("Employe Mail Id", next.getEmail());
				employes.put("Mobile", next.getMobile());
				employes.put("Employe Role", next.getEmpRole());
			}
		}
		else
		{
			return null;
		}
		return employes;
	}

	//Edit Employee
	@Override
	public Project updateEmploye(String employeId, Project project) {
		Project proj = repo.findByemployeId(employeId);
		
//		Project email = repo.findByEmail(project.getEmail());
//		if(email.getEmail().equalsIgnoreCase(proj.getEmail()))
//		{
//			throw new OrganizationNameAlreadyExistsException("This Mail already Eixts");
//		}
		if(proj != null)
		{
			proj.setEmail(project.getEmail());
			proj.setEmployeId(employeId);
			proj.setEmpRole(project.getEmpRole());
			proj.setMobile(project.getMobile());
			proj.setPassword(encode.encode(project.getPassword()));
			proj.setName(project.getName());
			repo.save(proj);
			return proj;
		}
		else
		{
			return null;
		}
	}

	@Override
	public String loginValidation(String email, String password, String role) {
		
		Project project = repo.findByEmail(email);
		if(project != null)
		{
			 if(encode.matches(password, project.getPassword()))
			 {
				 if(role.equalsIgnoreCase(project.getRole()))
				 {
					 if(role.equalsIgnoreCase("Super USER"))
					 {
						 return "Super User";
					 }
					 else if(role.equalsIgnoreCase("Admin"))
					 {
						 return "Admin";
					 }
					 else if(role.equalsIgnoreCase("USER"))
					 {
						 return "Employee";
					 }
					 else
					 {
						 return "role not registered";
					 }
				 }
				 else
				 {
					 return "Incorrect Role";
				 }
			 }
			 else
			 {
				 return "Invalid Password";
			 }
		}
		else
		{
			return "Invalid Email";
		}
	}
}
