package com.lostandfound.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lostandfound.dto.LoginRequest;
import com.lostandfound.dto.RegiesterRequest;
import com.lostandfound.model.User;
import com.lostandfound.service.AuthService;
import com.lostandfound.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:")
class AuthController{
	
	private final AuthService service;
	
	public AuthController(AuthService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public ApiResponse<Void> register(@Valid @RequestBody RegiesterRequest req) {
		service.register(req);
		return ApiResponse.success("User Registered Succesfully");
	}
	
	@PostMapping("/Login")
	public ApiResponse<User> login(@RequestBody LoginRequest req){
		User user = service.login(req);
		return ApiResponse.success("Login successfull", user);
	}
	
	
	
}