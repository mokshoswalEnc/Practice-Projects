package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.security.token.JWTUtil;

@Configuration
public class JwtConfig {
	@Bean
	public JWTUtil jwtUtil() {
		return new JWTUtil();
	}
}
