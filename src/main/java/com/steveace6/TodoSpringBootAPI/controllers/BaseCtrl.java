package com.steveace6.TodoSpringBootAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/rest/")
public class BaseCtrl {

//	@GetMapping("/")
	@RequestMapping(value="/")
	public String loginPage(){
		System.out.println("Login - GET");
		return "forward:app.html";
	}
	
	@GetMapping("/test")
	public String test(){
		System.out.println("Test -GET");
		return "forward:Home.html";
	}
}
