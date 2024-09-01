package com.sira.employe_monitoring_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sira.employe_monitoring_system.entity.Company;


public interface CompanyRepository extends MongoRepository<Company, String>{

	boolean existsByemail(String email);
	
	 Company findByEmail(String email);
	 
	 Company findByCompany(String name);
	 
	 
}
