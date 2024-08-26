package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.SuperUser;

public interface SuperUserRepository extends MongoRepository<SuperUser, String>{
	
	SuperUser findByemail(String email);
}
