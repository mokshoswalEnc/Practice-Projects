package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev")
public class DeveloperController {
	@GetMapping("/tools")
	public String devtools() {
		return "Hii Developer - secured endpoint";
	}
}
