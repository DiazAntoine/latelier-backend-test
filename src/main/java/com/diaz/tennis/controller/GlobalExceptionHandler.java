package com.diaz.tennis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	/**
	 * Handle Exception for all controllers of the application
	 * Precision : different behavior expected depending on the exception type
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    	HttpStatus httpResult = HttpStatus.INTERNAL_SERVER_ERROR;

		if (ex instanceof MethodArgumentNotValidException validationEx) {
			Map<String, String> errors = validationEx.getBindingResult()
					.getFieldErrors()
					.stream()
					.collect(Collectors.toMap(
							FieldError::getField,
							FieldError::getDefaultMessage
					));
			return ResponseEntity.badRequest().body(errors);
		}
    	if (ex instanceof IllegalArgumentException) {
    		httpResult = HttpStatus.BAD_REQUEST;
    	}
    	else if (ex instanceof EntityNotFoundException) {
    		httpResult = HttpStatus.NOT_FOUND;
    	}
        return ResponseEntity.status(httpResult).body(ex.getMessage()); 
    }

}
