package com.security.JwtSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.JwtSecurityDemo.entity.Role;
import com.security.JwtSecurityDemo.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtFilter;
	
	private final UserService userService;
	
	
	
	public SecurityConfig(JwtAuthenticationFilter jwtFilter, UserService userService) {
		super();
		this.jwtFilter = jwtFilter;
		this.userService = userService;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(request->request.requestMatchers("/api/v1/auth/**").permitAll()
				.requestMatchers("/api/v1/admin").hasAuthority(Role.ADMIN.name())
				.requestMatchers("/api/v1/user").hasAuthority(Role.USER.name())
				.anyRequest().authenticated()) 
				.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider()).addFilterBefore(
				jwtFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// TODO Auto-generated method stub
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService.userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	@Bean
	public  PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
