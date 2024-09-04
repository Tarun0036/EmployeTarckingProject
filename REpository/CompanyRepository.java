package com.example.SuperAdmin.REpository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SuperAdmin.Model.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
	List<Company> findByDeletedFalse();

	Company findBy_id(String _id);

	// List<Company> findByrole(String role);

	// List<Company> findByRoleAndNameOfOrganizationAndIsDeletedFalse(String
	// role, String nameOfOrganization);

	boolean existsByEmail(String email);

	boolean existsBycompany(String company);

	// Optional<User> findByEmail(String email);

//	Optional<User> findByAdminId(String adminId);
	List<Company> findByemail(String email);

}
