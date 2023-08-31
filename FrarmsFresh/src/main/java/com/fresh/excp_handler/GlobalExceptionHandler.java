package com.fresh.excp_handler;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fresh.custom_exceptions.ResourceNotFoundException;
import com.fresh.pojos.ApiResponse;

@ControllerAdvice // Mandatory cls level annotation to tell SC : following is global exc handler
					// (a spring bean)
					// class --to advise all controllers (RestController) , regarding exc handling
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// how to tell SC , that following is exc handling method
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in handle res not found...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	}
	
}
