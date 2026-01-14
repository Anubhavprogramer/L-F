package com.lostandfound.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lostandfound.dto.LoginRequest;
import com.lostandfound.dto.RegiesterRequest;
import com.lostandfound.model.User;
import com.lostandfound.repository.UserRepository;

@Service
public class AuthService {
	private final UserRepository repo;
	private final BCryptPasswordEncoder encoder;
	
	public AuthService(UserRepository repo, BCryptPasswordEncoder encoder ) {
		this.repo = repo;
		this.encoder = encoder;
	}
	
	public User register(RegiesterRequest req) {
		User user = new User();
		user.setEmail(req.getEmail());
		user.setName(req.getName());
		user.setPassword(encoder.encode(req.getPassword()));
		
		repo.save(user);
		
		return user;
		
	}
	
	public User login(LoginRequest req) {
		User user = repo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
		
		if(!encoder.matches(req.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credintial");
		}
		
		return user;
	}
}
