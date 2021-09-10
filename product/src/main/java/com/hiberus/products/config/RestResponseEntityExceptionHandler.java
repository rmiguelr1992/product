package com.hiberus.products.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hiberus.products.exception.ProductBadRequestException;
import com.hiberus.products.exception.ProductException;
import com.hiberus.products.exception.ProductNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ProductNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductException.class)
	protected ResponseEntity<Object> handleInternalError(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ProductBadRequestException.class)
	protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}