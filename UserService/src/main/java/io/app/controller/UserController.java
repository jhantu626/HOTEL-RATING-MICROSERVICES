package io.app.controller;

import io.app.models.User;
import io.app.payload.ApiResponse;
import io.app.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	/*
	* This Endpoint will perform create operation for User Creation
	* Requirement: User Body(Name,Email,About)
	* URI: POST-MAPPING /api/users
	*/
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
	}

	/*
	 * This Endpoint will perform  all users operation To get All Users
	 * Requirement: _,RatingService(UP),HotelService(UP)
	 * URI: GET-MAPPING /api/users
	 */
	@GetMapping
//	@CircuitBreaker(name = "RATING_HOTEL_ALL_BREAKER",fallbackMethod = "RatingHotelAllFallback")
	@Retry(name = "RATING_HOTEL_ALL_RETRY",fallbackMethod = "RatingHotelAllFallback")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(service.getAllUser());
	}
	/*
	*Circuit breaker implementation for get all user
	* to make circuit breaker fallback method we have to follow few things
	* that we have to give same return type as given in endpoint method. and
	* we have to add one extra parameter that is Exception.
	*/
	public ResponseEntity<List<User>> RatingHotelAllFallback(Exception ex){
		List<User> list=List.of(User.builder()
						.id("dummy")
						.name("dummy")
						.email("dummy@gmail.com")
						.about("This service is down for few internal server error")
				.build());
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	/*
	 * This Endpoint will perform get operation To get All Users
	 * Requirement: _,HotelService(UP),RatingService(UP)
	 * URI: GET-MAPPING /api/users
	 * Implemented: CircuitBreaker,RateLimiter
	 */
	@GetMapping("/{id}")
	@CircuitBreaker(name = "RATING_HOTEL_BREAKER", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId){
		return ResponseEntity.ok(service.getUser(userId));
	}
	//FallBack method of RATING_HOTEL_BREAKER fallback
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		User user=User.builder()
				.id(userId)
				.name("Dummy Poddar")
				.email("dummy@gmail.com")
				.about("This service is down for internal server error!")
				.build();
		return ResponseEntity.ok(user);
	}


	//GetUserByEmailId
	@GetMapping("/email/{email}")
//	@CircuitBreaker(name = "RATING_HOTEL_EMAIL_BREAKER",fallbackMethod = "ratingHotelEmailFallback")
	@RateLimiter(name = "RATING_HOTEL_EMAIL_RATELIMITER",fallbackMethod = "ratingHotelEmailFallback")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(service.getUserByEmail(email));
	}
	//fallback method for RATING_HOTEL_EMAIL_BREAKER
	public ResponseEntity<User> ratingHotelEmailFallback(Exception e){
		User user=User.builder().id("dummyId")
				.name("Dummy")
				.email("dummy@gmail.com")
				.about("This service is down for internal server error").build();
		return ResponseEntity.ok(user);
	}

	//UpdateUserById
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserById(@PathVariable("userId") String userId,
											   @RequestBody User user){
		return ResponseEntity.ok(service.updateUser(userId,user));
	}

	//UpdateUserByEmail
	@PutMapping("/email/{email}")
	public ResponseEntity<User> updateUserByEmail(@PathVariable("email") String email,
											   @RequestBody User user){
		return ResponseEntity.ok(service.updateUserByEmail(email,user));
	}


	//DeleteUserById
	int retry=1;
	@DeleteMapping("/{userId}")
	@Retry(name = "RATING_DELETE_BREAKER",fallbackMethod = "ratingDeleteFallback")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") String userId){
		System.out.println(retry++);
		return ResponseEntity.ok(service.deleteUserById(userId));
	}
	//fallback method of ratingDeleteFallback
	public ResponseEntity<ApiResponse> ratingDeleteFallback(Exception ex){
		ApiResponse apiResponse= ApiResponse.builder()
				.msg("Can't delete for internal server error")
				.success(false).build();
		return ResponseEntity.ok(apiResponse);
	}


	//DeleteUserByEmail
	@DeleteMapping("/email/{email}")
	@CircuitBreaker(name = "RATING_DELETE_EMAIL_BREAKER",fallbackMethod = "ratingDeleteEmailFallback")
	public ResponseEntity<ApiResponse> deleteUserByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(service.deleteByEmail(email));
	}

	//Fallback method of RATING_DELETE_EMAIL_BREAKER
	public ResponseEntity<ApiResponse> ratingDeleteEmailFallback(String email,Exception ex){
		ApiResponse apiResponse=ApiResponse.builder()
				.msg("Can't delete for internal server error")
				.success(false).build();
		return ResponseEntity.ok(apiResponse);
	}



}








