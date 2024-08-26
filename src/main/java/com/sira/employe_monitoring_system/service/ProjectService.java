package com.sira.employe_monitoring_system.service;

import java.util.List;
import java.util.Map;

import com.sira.employe_monitoring_system.entity.Project;

public interface ProjectService {

	Project addOrganistaion(Project project);

	List<Project> getAllOrganizations();

	List<Project> getAllAdmins();

	Map<String, String> getAllEmployes();

	Project updateEmploye(String employeId, Project project);

	boolean updateOrganization(String name, Project userDto);

	boolean deleteOrganization(String name);

	Project getByName(String name);

	Project addSuperUser(Project project);

	Project addAdmin(Project project);

	Project addEmploe(Project project);

	//Project loginValidation(String email, String password, String role);

	String loginValidation(String email, String password, String role);
}
