package com.cd.advice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserManagementAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException fileException){
		return new ResponseEntity<String>(fileException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
