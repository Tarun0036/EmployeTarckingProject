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
		
		return new InMemoryUserDetailsManager(admin,user );
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
		return http.csrf().
				disable().
				authorizeHttpRequests().
				requestMatchers("/admin/add","admin/login","admin/get/**", "organization/register").
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
