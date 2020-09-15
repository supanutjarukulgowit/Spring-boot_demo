package com.example.stock_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**"); // allow all path
//		registry.addMapping("/product/*").allowedMethods("GET", "POST"); only allow path product method get,post
//		registry.addMapping("/product/*").allowedMethods("*"); allow all http method
//		registry.addMapping("/product/*").allowedMethods("GET", "POST").allowedOrigins("", "", ""); allow origins

	}
}
