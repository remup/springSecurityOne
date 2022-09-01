package com.remya.springsecurity.SpringSecurityOne.model;

import org.springframework.stereotype.Component;

@Component
public class ExceptionMessage {
private String errorMessage;

public ExceptionMessage() {
	
}

public String getErrorMessage() {
	return errorMessage;
}

public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
}
