package com.example.SuperAdmin.Exceptionhandling;

public class EmpAlreadyExistsException extends RuntimeException {
	public EmpAlreadyExistsException(String message) {
		super(message);
	}

}
