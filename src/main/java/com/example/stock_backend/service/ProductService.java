package com.example.stock_backend.service;

import java.util.List;

import com.example.stock_backend.Model.Product;
import com.example.stock_backend.controller.request.ProductRequest;

public interface ProductService {

	List<Product> getAllProduct();

	Product getProductById(Long id);

	Product createProduct(ProductRequest productRequest);

	Product updateProduct(ProductRequest productRequest, Long id);

	void deleteProduct(Long id);

	Product getProductByName(String name);

	List<Product> getProductByNameAndStock(String name, int stock);

	List<Product> getProductOutOfStock();

	List<Product> getProductByNameAndPrice(String name, int price);

}
