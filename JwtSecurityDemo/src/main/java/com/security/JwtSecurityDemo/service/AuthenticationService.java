package com.security.JwtSecurityDemo.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.security.JwtSecurityDemo.dto.JwtAuthenticationResponse;
import com.security.JwtSecurityDemo.dto.RefreshTokenRequest;
import com.security.JwtSecurityDemo.dto.SignInRequest;
import com.security.JwtSecurityDemo.dto.SignUpRequest;
import com.security.JwtSecurityDemo.entity.User;

public interface AuthenticationService {
	public User signUp(SignUpRequest signUpRequest);
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest req);
	public JwtAuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest req);
}
