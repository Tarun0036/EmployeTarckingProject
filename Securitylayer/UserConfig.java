package com.example.SuperAdmin.Securitylayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfig {


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(
	                    "/user/post",
	                    "/user/admins/{company}",
	                    "/user/get/{email}",
	                    "/user/update/{_id}",
	                    "/user/delete/{_id}",
	                    "/Organizations/post",
	                    "/Organizations/Company",
	                    "/Organizations/organization/{_id}",
	                    "/user/create",
	                    "/user/create-user",
	                    "/user/login"
	                ).permitAll() // Allow public access to these endpoints
	                .anyRequest().authenticated() // Require authentication for all other requests
	            )
	            .formLogin()
	                .loginPage("/login") // Specify your login page URL
	                .permitAll() // Allow access to the login page without authentication
	            .and()
	            .logout()
	                .permitAll(); // Allow access to logout functionality without authentication

	        return http.build();
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // Define BCryptPasswordEncoder bean
	    }
	}



