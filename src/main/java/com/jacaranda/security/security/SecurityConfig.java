package com.jacaranda.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jacaranda.security.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// Método que usaremos más abajo
    @Bean
    UserService userDetailsService() {
		return new UserService();
	}
	// Método que nos suministrará la codificación
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> {
			requests.requestMatchers("/").permitAll()
			.requestMatchers("/webjars/**").permitAll()
			.requestMatchers("/books").authenticated()
			.requestMatchers("/books/create").hasAnyAuthority("user","admin")
			.requestMatchers("/books/edit").hasAnyAuthority("user","admin")
			.requestMatchers("/books/delete/**").hasAuthority("admin")	
			.requestMatchers("/users").authenticated()
			.requestMatchers("/users/create").hasAnyAuthority("user","admin")
			.requestMatchers("/users/edit").hasAnyAuthority("user","admin")
			.requestMatchers("/users/delete/**").hasAuthority("admin")	
			.requestMatchers("/error").permitAll()
			.anyRequest().denyAll();
		}).formLogin((form) -> form
				.permitAll())
				.logout((logout) -> logout.permitAll());
		return http.build();
	}
}
