package com.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.token.JWTUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public static class AuthRequest{
		@NotBlank
		private String username;
		@NotBlank
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	@PostMapping("/login")
	public Map<String, Object> login(@Valid @RequestBody AuthRequest request){
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if(auth.isAuthenticated()) {
			String token = jwtUtil.generateTokens(request.getUsername());
			Map<String, Object> resp = new HashMap<>();
			resp.put("token", token);
			resp.put("type", "Bearer");
			resp.put("username", request.getUsername());
			resp.put("expiresIn", (int) (jwtUtilExpirySeconds()));
			return resp;
		}
		throw new RuntimeException("Invalid Credentials");
	}
	
	private long jwtUtilExpirySeconds() {
		return (jwtUtil != null ? (jwtUtilExpirationMs() / 1000L) : 0L);
	}
	
	private long jwtUtilExpirationMs() {
		return 3600_000L;
	}
}
