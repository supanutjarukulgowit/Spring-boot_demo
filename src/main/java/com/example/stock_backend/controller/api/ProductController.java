package com.example.stock_backend.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock_backend.Model.Product;
import com.example.stock_backend.controller.request.ProductRequest;
import com.example.stock_backend.exception.ValidationException;
import com.example.stock_backend.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController // for @Controller and @ResponseBody
@RequestMapping("/product")
//@CrossOrigin
@Slf4j // for Logger
public class ProductController {

	private final ProductService productService;

	ProductController(ProductService productService) {
		this.productService = productService;
	}

//	@RequestMapping(path = "/say", method = RequestMethod.GET)
	@GetMapping()
	public List<Product> getProducts() {
		return productService.getAllProduct();
	}

//	@RequestMapping(path = "/say", method = RequestMethod.DELETE)
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable long id) {
		return productService.getProductById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Product addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}
		return productService.createProduct(productRequest);
	}

	@PutMapping("/{id}")
	public Product editProduct(@Valid ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable long id) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			});
		}

		return productService.updateProduct(productRequest, id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
	}

	@GetMapping(path = "/search", params = "name")
	public Product searchProductByName(@RequestParam String name) {
		return productService.getProductByName(name);
	}

	@GetMapping(path = "/search", params = { "name", "stock" })
	public List<Product> searchProductByNameAndStock(@RequestParam String name, @RequestParam int stock) {
		return productService.getProductByNameAndStock(name, stock);
	}

	@GetMapping("/checkOutOfStock")
	public List<Product> checkOutOfStock() {
		return productService.getProductOutOfStock();
	}

	@GetMapping(path = "/search", params = { "name", "price" })
	public List<Product> searchProductByNameAndPrice(@RequestParam String name, @RequestParam int price) {
		return productService.getProductByNameAndPrice(name, price);
	}
}
