package io.rating.app.exception;

import io.rating.app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleNotFoundEx(ResourceNotFoundException ex){
		return ResponseEntity.ok(
				ApiResponse.builder()
						.msg(ex.getMessage())
						.status(HttpStatus.NOT_FOUND).build()
		);
	}
}
