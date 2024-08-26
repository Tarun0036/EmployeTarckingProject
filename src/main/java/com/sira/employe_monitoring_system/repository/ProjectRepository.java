package com.sira.employe_monitoring_system.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

	Project findByEmail(String email);
	
	List<Project> findByrole(String role);
	
	Optional<Project> findByName(String name);
	
	boolean existsByemail(String email);
	
	Project findByemployeId(String employeId);
}
