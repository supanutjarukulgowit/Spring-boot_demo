package com.example.stock_backend.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

	@NotEmpty
	@Size(min = 2, max = 100)
	private String name;
	private MultipartFile image;
	private int price;
	private int stock;

}
