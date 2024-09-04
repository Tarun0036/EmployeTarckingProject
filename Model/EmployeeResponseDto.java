package com.example.SuperAdmin.Model;

import org.springframework.data.annotation.Id;

public class EmployeeResponseDto {
	public EmployeeResponseDto(String get_id, String name2, String email2, String company2, String contact2,
			String employeId2, String empRole2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	private String _id;
	private String name;
	private String email;
	private String password;
	private String empRole;
	private String employeId;
	private String Contact;
	private boolean isDeleted;
	private String company;

}
