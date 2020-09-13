package com.example.stock_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.stock_backend.service.StorageService;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
public class StockBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockBackendApplication.class, args);

	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return agrs -> {
			storageService.init();
		};
	}
}
