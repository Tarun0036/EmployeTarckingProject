package com.sira.employe_monitoring_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.SuperUser;
import com.sira.employe_monitoring_system.repository.SuperUserRepository;

@Service
public class SuperUserServiceImplimentation implements SuperUserService{

	@Autowired
	private SuperUserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	@Override
	public SuperUser addSuperUser(SuperUser superUser) {
		superUser.setPassword(encode.encode(superUser.getPassword()));
		repo.save(superUser);
		return repo.save(superUser);
	}
	@Override
	public boolean checkCredentials(String email, String password, String role) {
		SuperUser superUser = repo.findByemail(email);
		if(superUser != null)
		{
			if(encode.matches(password, superUser.getPassword()))
			{
				if(role.equalsIgnoreCase(superUser.getRole()))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

}
