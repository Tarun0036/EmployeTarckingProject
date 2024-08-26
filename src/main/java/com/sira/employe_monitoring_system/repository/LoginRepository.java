package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sira.employe_monitoring_system.entity.Login;

@Repository
public interface LoginRepository extends MongoRepository<Login, String> {
	
	Login  findByEmail(String email);
}
