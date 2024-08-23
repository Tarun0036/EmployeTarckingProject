package com.sira.employe_monitoring_system.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.Admin;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.repository.AdminRepository;

@Service
public class AdminServiceImplimentation implements AdminService{

	private final AdminRepository repo;
    private final BCryptPasswordEncoder encoder;

    public AdminServiceImplimentation(AdminRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }
	
	@Override
	public Admin addAdmin(Admin admin) {
		
		if(admin.getEmail().isEmpty() || admin.getPassword().isEmpty())
		{
			new MandatoryFeildExcetion("Fill all Feilds Properly");
		}
		admin.setPassword(encoder.encode(admin.getPassword()));
		return repo.save(admin);
	}

    public boolean adminLogin(String email, String rawPassword) {
        Optional<Admin> optional = repo.findByemail(email);

        if (optional.isPresent()) {
            Admin admin = optional.get();

            return encoder.matches(rawPassword, admin.getPassword());
        }
        return false;
    }
	@Override
	public Admin getAdminByEmail(String email) {
		
		 Optional<Admin> optional = repo.findByemail(email);
		 return optional.get();
	}

}
