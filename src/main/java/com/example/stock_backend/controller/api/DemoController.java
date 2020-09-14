package com.example.stock_backend.controller.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.stock_backend.util.DateUtils;

//@RestController
public class DemoController {

	DateUtils date;
	private SayService sayService;

	public DemoController(DateUtils date, @Qualifier("cat_test") SayService sayService) {
		this.date = date;
		this.sayService = sayService;
	}

	@GetMapping("/")
	String getToDay() {

		return date.toDayString();
	}

	@GetMapping("/say")
	String say() {
		return sayService.say();
	}
}

interface SayService {
	String say();
}

@Component("cat_test")
class Cat implements SayService {

	@Override
	public String say() {
		return "meow";
	}

}

@Component
class Dog implements SayService {

	@Override
	public String say() {
		return "hoof";
	}

}
