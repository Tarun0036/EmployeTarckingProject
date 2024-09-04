package com.example.SuperAdmin.Exceptionhandling;

public class OrganizationAlreadyExistsException extends RuntimeException {

	public OrganizationAlreadyExistsException(String message) {
		super(message);
	}

}
