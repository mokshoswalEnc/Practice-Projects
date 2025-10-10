package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "Hello Admin - secured endpoint!";
	}
	
	@GetMapping("/stats")
	public String adminStats() {
		return "Admin stats..";
	}
}
