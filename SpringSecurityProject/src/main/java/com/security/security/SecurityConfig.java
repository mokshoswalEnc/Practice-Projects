package com.security.security;

import com.security.config.JwtConfig;
import com.security.token.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JWTUtil JWTUtil;

    private final JwtConfig jwtConfig;

    SecurityConfig(JwtConfig jwtConfig, JWTUtil JWTUtil) {
        this.jwtConfig = jwtConfig;
        this.JWTUtil = JWTUtil;
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		manager.createUser(User.withUsername("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN")
				.build());
		
		manager.createUser(User.withUsername("dev")
				.password(encoder.encode("developer123"))
				.roles("DEVELOPER")
				.build());
		
		manager.createUser(User.withUsername("user")
				.password(encoder.encode("user123"))
				.roles("USER")
				.build());
		return manager;
	}
	
	@Bean
	public AuthenticationProvider authProvider(UserDetailsService uds, PasswordEncoder encoder) {
		DaoAuthenticationProvider p = new DaoAuthenticationProvider();
		p.setUserDetailsService(uds);
		p.setPasswordEncoder(encoder);
		return p;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/login").permitAll()
					.requestMatchers("/api/admin/**").hasRole("ADMIN")
					.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN", "DEVELOPER")
					.requestMatchers("/api/dev/**").hasAnyRole("DEVELOPER","ADMIN")
					.anyRequest().authenticated()
					)
					.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
