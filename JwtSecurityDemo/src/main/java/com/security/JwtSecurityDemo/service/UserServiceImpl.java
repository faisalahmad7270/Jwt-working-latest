package com.security.JwtSecurityDemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.JwtSecurityDemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepo;
	
	
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		}
	};
	}
		}


