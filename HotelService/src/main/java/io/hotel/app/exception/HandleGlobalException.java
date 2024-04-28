package io.hotel.app.exception;

import io.hotel.app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleGlobalException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleNotFoundException(ResourceNotFoundException ex){
		ApiResponse apiResponse= ApiResponse.builder()
				.msg(ex.getMessage())
				.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(apiResponse);
	}
}
