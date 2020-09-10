package com.example.stock_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock_backend.Model.Product;

@RestController // for @Controller and @ResponseBody
@RequestMapping("/product")
public class ProductController {

//	@RequestMapping(path = "/say", method = RequestMethod.GET)
	@GetMapping()
	public String getProducts() {
		return "something product ALL";
	}

//	@RequestMapping(path = "/say", method = RequestMethod.DELETE)
	@GetMapping("/{id}")
	public String getProduct(@PathVariable long id) {
		return "something product id : " + id;
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

	@PostMapping()
	public Product addProduct(@RequestBody Product product) {
		return product;
	}
}
