package com.sira.employe_monitoring_system.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sira.employe_monitoring_system.entity.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByEmail(String email);

	boolean existsByEmail(String email);

	List<Users> findByRoleAndCompany(String role, String name);

	Users findByEmployeId(String employeId);
	
	List<Users> findByCompany(String company);
	
	
	List<Users>  findByCompanyId(String companyId);
	
	Users findByAdminId(String adminId);
	
	boolean existsByAdminId(String adminId);
	
	boolean existsByEmployeId(String employeId);
}
