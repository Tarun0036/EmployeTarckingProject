package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.EmployeLoginDetails;

public interface EmployeLoginDetailsRepository extends MongoRepository<EmployeLoginDetails, String>{

	
	EmployeLoginDetails findByEmployeId(String employeId);
	
}
