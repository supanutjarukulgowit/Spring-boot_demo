package com.example.stock_backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;

//@Configuration
//@Scope("prototype")
public class DateUtils {

	private static SimpleDateFormat simpleDateFOrmat = new SimpleDateFormat("dd-MM-yyy");

	public DateUtils() {
		System.out.println("object created");
	}

	@Bean
	public String toDayString() {
		return simpleDateFOrmat.format(new Date());
	}

	@PostConstruct
	public void init() throws Exception {
		System.out.println("object init");
	}

	@PreDestroy
	public void destroy() throws Exception {
		System.out.println("object destroy");
	}

}
