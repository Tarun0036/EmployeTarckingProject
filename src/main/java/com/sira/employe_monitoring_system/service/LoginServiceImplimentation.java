package com.sira.employe_monitoring_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.Login;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.repository.LoginRepository;

@Service
public class LoginServiceImplimentation implements LoginService{

	@Autowired
	private LoginRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Override
	public Login addOne(Login login) {
		
		
		 if(login.getName().isEmpty()||login.getEmail().isEmpty()||login.getPassword().isEmpty()||login.getPassword().isEmpty() || 
				login.getRole().isEmpty() || login == null || login.getEmail()==null || login.getName()== null || login.getPassword() == null || login.getRole() == null)
		{
			throw new MandatoryFeildExcetion("All Feilds are Mandatory");
		}
		else
		{
			login.setPassword(encode.encode(login.getPassword()));
			return repo.save(login);
		}
	}

	@Override
	public Login validateCredentials(String email, String password, String role) {
	    Login login = repo.findByEmail(email);
	    if (login == null || !encode.matches(password, login.getPassword())) {
	        //throw new RuntimeException("Incorrect credentials");
	    	return null;
	    }
	    if (login.getRole().equalsIgnoreCase(role)) {
	        return login;
	    } else {
	        return null;
	    }
	}

	@Override
	public Login getByEmail(String email) {
		Login login = repo.findByEmail(email);
		return login;
	}
}
