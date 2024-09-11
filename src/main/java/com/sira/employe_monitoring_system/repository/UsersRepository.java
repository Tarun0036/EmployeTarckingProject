package com.sira.employe_monitoring_system.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sira.employe_monitoring_system.entity.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByEmail(String email);

	boolean existsByEmail(String email);

	List<Users> findByRoleAndCompany(String role, String name);

	Users findByEmployeId(String employeId);
	
	List<Users> findByCompany(String company);
	
	
	List<Users>  findByCompanyId(String companyId);
	
	List<Users> findByAdminId(String adminId);
	
	boolean existsByAdminId(String adminId);
	
	boolean existsByEmployeId(String employeId);
	
	@Query("{'role': {$ne: 'Super User'}, 'isDeleted': false}")
	List<Users> findAllByRoleNotAndIsDeletedFalse();
	
	List<Users> findByCompanyIdAndIsDeletedFalse(String companyId);
	
	List<Users> findByRoleAndAdminIdAndIsDeletedFalse(String role, String adminId);
	
	List<Users> findByRoleAndIsDeletedFalse(String role);
	
	List<Users> findByRoleAndCompanyInAndIsDeletedFalse(String role, List<String> companies);
}
