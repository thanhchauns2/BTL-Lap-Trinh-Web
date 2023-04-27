package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomePageController {
	
	@GetMapping
	public String index() {
		System.out.println("Hello");
		return "/main/index";
	}
}
