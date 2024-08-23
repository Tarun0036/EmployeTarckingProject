package com.sira.employe_monitoring_system.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String>{

	Optional<Admin> findByemail(String email);
}
