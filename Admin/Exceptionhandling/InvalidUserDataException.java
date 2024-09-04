package com.example.SuperAdmin.Exceptionhandling;

public class InvalidUserDataException extends RuntimeException {
	public InvalidUserDataException(String msg) {
		super(msg);
	}

}
