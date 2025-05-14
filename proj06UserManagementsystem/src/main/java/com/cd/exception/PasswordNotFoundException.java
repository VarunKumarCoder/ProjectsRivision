package com.cd.exception;

public class PasswordNotFoundException extends Exception {

	public PasswordNotFoundException() {
		super("Password Not Found");
	}
	 public PasswordNotFoundException(String message) {
	        super(message);
	    }
	 public PasswordNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
