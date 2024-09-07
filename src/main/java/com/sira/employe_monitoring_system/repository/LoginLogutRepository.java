package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.LoginLogutDetails;

public interface LoginLogutRepository extends MongoRepository<LoginLogutDetails, String>{

	LoginLogutDetails findByEmployeId(String employeId);
}
