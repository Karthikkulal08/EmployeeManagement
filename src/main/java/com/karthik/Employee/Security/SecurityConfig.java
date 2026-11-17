//package com.karthik.Employee.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
//		return http
//				.csrf(csrf -> csrf.disable()) // Required for POST /login to work
//				.sessionManagement(session -> session
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Standard for JWT
//				)
//				.authorizeHttpRequests(auth -> auth
//						// 1. ALLOW THESE WITHOUT LOGIN
//						.requestMatchers("/", "/login", "/index.html", "/static/**").permitAll()
//
//						// 2. PROTECT EVERYTHING ELSE
//						.anyRequest().authenticated()
//				)
//				// 3. RUN JWT FILTER FIRST
//				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//
//				// 4. DISABLE THE BUILT-IN UI
//				.formLogin(form -> form.disable())
//				.httpBasic(basic -> basic.disable())
//				.build();
//	}
//
//}
