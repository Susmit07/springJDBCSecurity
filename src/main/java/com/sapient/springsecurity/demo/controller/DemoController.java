package com.sapient.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	// Adding request mapping for leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	
	// Adding request mapping for admins
	@GetMapping("/systems")
	public String showAdmins() {
		return "admins";
	}
}
