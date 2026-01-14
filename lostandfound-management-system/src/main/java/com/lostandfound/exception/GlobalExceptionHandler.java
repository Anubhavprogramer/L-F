package com.lostandfound.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lostandfound.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ApiResponse<Void> handleRuntimeException(RuntimeException ex){
		return ApiResponse.error(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException ex){
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();
		
		return ApiResponse.error(message);
	}
}
