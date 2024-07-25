package com.security.JwtSecurityDemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.JwtSecurityDemo.dto.JwtAuthenticationResponse;
import com.security.JwtSecurityDemo.dto.RefreshTokenRequest;
import com.security.JwtSecurityDemo.dto.SignInRequest;
import com.security.JwtSecurityDemo.dto.SignUpRequest;
import com.security.JwtSecurityDemo.entity.User;
import com.security.JwtSecurityDemo.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	private final AuthenticationService authenticationService;
	
	
	
	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}



	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest req) {
		return ResponseEntity.ok(authenticationService.signUp(req));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest req) {
		return ResponseEntity.ok(authenticationService.signin(req));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest req) {
		return ResponseEntity.ok(authenticationService.refreshToken(req));
	}
}
