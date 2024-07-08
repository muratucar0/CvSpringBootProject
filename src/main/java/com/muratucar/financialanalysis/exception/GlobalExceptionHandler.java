package com.muratucar.financialanalysis.exception;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodNotValidExceptionHandler(MethodArgumentNotValidException argumentNotValidException){
	     String errorMessage = argumentNotValidException.getBindingResult()
	    		 .getFieldErrors().stream()
	    		 .map(err-> err.getField() + " " + err.getDefaultMessage())
	    		 .collect(Collectors.joining(", "));
	     
	     return ResponseEntity.badRequest().body(errorMessage);
		
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<String> companyNotFoundExceptionHandler(CompanyNotFoundException companyNotFoundException){
		
		  return ResponseEntity.badRequest().body(companyNotFoundException.getMessage());
		
	}
	
}
