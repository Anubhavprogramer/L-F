package com.lostandfound.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.dto.LoginRequest;
import com.lostandfound.dto.RegiesterRequest;
import com.lostandfound.model.User;
import com.lostandfound.security.JwtUtil;
import com.lostandfound.service.AuthService;
import com.lostandfound.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:")
class AuthController{
	
	private final AuthService service;
	private final JwtUtil jwtUtil;
	
	public AuthController(AuthService service, JwtUtil jwtUtil) {
		this.service = service;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/register")
	public ApiResponse<String> register(@Valid @RequestBody RegiesterRequest req) {
		User user = service.register(req);
		String token = jwtUtil.generateToken(user.getEmail());
		return ApiResponse.success("User Registered Succesfully", token);
	}
	
	@PostMapping("/Login")
	public ApiResponse<String> login(@RequestBody LoginRequest req){
		User user = service.login(req);
		
		String token = jwtUtil.generateToken(user.getEmail());
		return ApiResponse.success("Login successfull", token);
	}
}