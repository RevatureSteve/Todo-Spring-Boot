package com.revature.TodoSpringBootAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/rest/")
public class BaseCtrl {

//	@GetMapping("/") - shorthand for Get RequestMapping
	@RequestMapping(value="/")
	public String loginPage(){
		System.out.println("Login - GET");
		return "forward:app.html";
	}
	
	@GetMapping("/home")
	public String test(){
		System.out.println("Home -GET");
		return "forward:Home.html";
	}
	
	@GetMapping("/user")
	public String userMapping(){
		System.err.println("User -GET");
		return "forward:User.html";
	}
	
	@GetMapping("/admin")
	public String adminMapping(){
		System.err.println("Admin -GET");
		return "forward:Admin.html";
	}
}
