package com.sira.employe_monitoring_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MandatoryFeildExcetion.class)
	public ResponseEntity<String> handleMandatoryFeildException(MandatoryFeildExcetion ex)
	{
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrganizationNameAlreadyExistsException.class)
    public ResponseEntity<String> handleOrganizationNameAlreadyExistsException(OrganizationNameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
