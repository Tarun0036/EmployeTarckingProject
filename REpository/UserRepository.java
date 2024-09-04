package com.example.SuperAdmin.REpository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SuperAdmin.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByDeletedFalse();

	List<User> findByadminId(int adminId);

	List<User> findByrole(String role);

	List<User> findByCompany(String company);

	boolean existsByEmail(String email);

	boolean existsByAdminId(int adminId);

	boolean existsByEmployeId(String employeId);
	// Optional<User> findByEmail(String email);

//	Optional<User> findByAdminId(String adminId);
	List<User> findByemail(String email);

	List<User> findByCompanyId(String companyId);

	// loginrepo

	User findByEmail(String email);

	void deleteByEmail(String email);

	Object findByRole(String string);

	boolean existsByRole(String string);

	boolean existsByAdminId(String employeId);

}
