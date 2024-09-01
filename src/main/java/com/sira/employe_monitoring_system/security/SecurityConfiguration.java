package com.sira.employe_monitoring_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails admin = User.withUsername("admin")
				.password(bCryptPasswordEncoder().encode("Admin@123"))
				.roles("ADMIN").build();
		
		UserDetails user = User.withUsername("user")
				.password("User@123")
				.roles("USER").build();
		
		UserDetails superUser = User.withUsername("Super User")
				.password("SuperUser@123")
				.roles("Super User").build();
		
		return new InMemoryUserDetailsManager(admin,user );
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
		return http.csrf().
				disable().
				authorizeHttpRequests().
				requestMatchers("/project/organizationRegitration", "/project/edit/**", "/project/delete/**",
						 "/project/allOrganizations", "/users/addSuperUser","/users/addAdmin", "/users/addEmploye",
						 "/users/loginValidation","/users/employesInCompany/**", "/project/getByName/**",
						"/users/editEmploye/**", "/users/allEmployes/**", "/users/getAmdins/**", "users/getCompany/**"
						,"/employe/logintTime/**", "/employe/postLoginDetails", "users/getAdminByCompanyId/**", "/employe/logout/**"
						, "/users/editAdmin/**", "/users/deleteAdmin/**", "/users/validate").
				permitAll()
				.and().authorizeHttpRequests().
				requestMatchers("/admin/find/**").
				authenticated().and().
				formLogin().and().
				build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
