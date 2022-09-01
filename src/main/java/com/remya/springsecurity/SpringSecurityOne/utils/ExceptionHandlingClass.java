package com.remya.springsecurity.SpringSecurityOne.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.remya.springsecurity.SpringSecurityOne.model.ExceptionMessage;

@RestControllerAdvice
public class ExceptionHandlingClass extends ResponseEntityExceptionHandler{

	@Autowired
	ExceptionMessage message ;
	
//	@ExceptionHandler(BadCredentialsException.class)
//	public ResponseEntity<ExceptionMessage> badExceptionMethod(Exception e)
//	{
//		HttpHeaders header = new HttpHeaders();	
//		header.set("operation", "failed");
//		message.setErrorMessage(e.getMessage()+"badcredentials");
//		
//		return new ResponseEntity<ExceptionMessage>(message,header,HttpStatus.BAD_REQUEST);
//		
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ExceptionMessage> exceptionMethod(Exception e)
//	{
//		HttpHeaders header = new HttpHeaders();	
//		header.set("operation", "failed");
//		message.setErrorMessage(e.getMessage());
//		
//		return new ResponseEntity<ExceptionMessage>(message,header,HttpStatus.BAD_REQUEST);
//		
//	}
//	
	
}

