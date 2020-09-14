package com.example.stock_backend.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock_backend.Model.Product;
import com.example.stock_backend.controller.request.ProductRequest;
import com.example.stock_backend.exception.ProductNotFoundException;
import com.example.stock_backend.service.StorageService;

@RestController // for @Controller and @ResponseBody
@RequestMapping("/product")
public class ProductController {

	private final AtomicLong counter = new AtomicLong();
	private List<Product> products = new ArrayList<>();
	private StorageService storageService;

	ProductController(StorageService storageService) {
		this.storageService = storageService;

	}

//	@RequestMapping(path = "/say", method = RequestMethod.GET)
	@GetMapping()
	public List<Product> getProducts() {
		return products;
	}

//	@RequestMapping(path = "/say", method = RequestMethod.DELETE)
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable long id) {
		return products.stream().filter(result -> result.getId() == id).findFirst()
				.orElseThrow(() -> new ProductNotFoundException(id));
	}

//	@GetMapping({ "/say/{id}/name/{name}", "/sayname/{id}" })
//	public String getProductByName(@PathVariable(name = "id") long id, @PathVariable(required = false) String name) {
//		return name + " product id : " + id;
//	}
//
//	@GetMapping({ "/say/print" })
//	public String getProductByNameQuery(
//			@RequestParam(name = "name", required = false, defaultValue = "default name") String name) {
//		return name;
//	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Product addProduct(ProductRequest productRequest) {
		String fileName = storageService.store(productRequest.getImage());
		Product data = new Product(counter.incrementAndGet(), productRequest.getName(), fileName,
				productRequest.getPrice(), productRequest.getStock());
		products.add(data);
		return data;
	}

	@PutMapping("/{id}")
	public void editProduct(@RequestBody Product product, @PathVariable long id) {
		Product data;
		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
			result.setName(product.getName());
			result.setImage(product.getImage());
			result.setPrice(product.getPrice());
			result.setStock(product.getStock());

		}, () -> {// to do if exception
			throw new ProductNotFoundException(id);
		});

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		Product data;
		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {

			products.remove(result);

		}, () -> {// to do if exception
			throw new ProductNotFoundException(id);
		});
	}
}
