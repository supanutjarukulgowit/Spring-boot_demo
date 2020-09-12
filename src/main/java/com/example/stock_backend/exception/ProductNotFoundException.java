package com.example.stock_backend.exception;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(Long id) {
		super("Product id not found : " + id);
	}
}
