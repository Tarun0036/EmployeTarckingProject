package com.example.SuperAdmin.Exceptionhandling;

public class MissingFieldException extends RuntimeException {
	public MissingFieldException(String message) {
		super(message);

	}

}
