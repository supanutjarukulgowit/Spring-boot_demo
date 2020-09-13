package com.example.stock_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND) // for http status 404
	String handleProductNotFound(ProductNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // for http status 500
	String handleStorageExcaption(StorageException ex) {
		return ex.getMessage();
	}
}
