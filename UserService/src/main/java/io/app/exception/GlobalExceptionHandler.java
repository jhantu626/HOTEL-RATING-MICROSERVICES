package io.app.exception;

import io.app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResouceNotFoundException(ResourceNotFoundException ex){
		ApiResponse apiResponse=ApiResponse.builder()
				.msg(ex.getMessage())
				.success(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}

}







