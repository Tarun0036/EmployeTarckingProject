package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.Organistion;

public interface OrganizationRepository extends MongoRepository<Organistion, String>{
	
	boolean existsBynameOfOrganization(String name);
}
//mongodb://localhost:27017/