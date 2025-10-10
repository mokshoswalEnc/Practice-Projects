package com.security.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.token.JWTUtil;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final PasswordEncoder passwordEncoder;

    private final SecurityConfig securityConfig;
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER = "Bearer ";

    JwtFilter(SecurityConfig securityConfig, PasswordEncoder passwordEncoder) {
        this.securityConfig = securityConfig;
        this.passwordEncoder = passwordEncoder;
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(AUTH_HEADER);
		if(header != null && header.startsWith(BEARER)) {
			String token = header.substring(BEARER.length());
			try {
				if(jwtUtil.validateToken(token)) {
					String username = jwtUtil.extractUserName(token);
					if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						UserDetails user = userDetailsService.loadUserByUsername(username);
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (JwtException e) {
				System.out.println("Invalid or expired token");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
}
