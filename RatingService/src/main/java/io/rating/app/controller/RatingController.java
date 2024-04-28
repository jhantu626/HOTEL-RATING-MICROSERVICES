package io.rating.app.controller;

import io.rating.app.models.Rating;
import io.rating.app.payload.ApiResponse;
import io.rating.app.service.RatingService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	@Autowired
	private RatingService service;

	/*
	* This endpoint will perform create rating
	* URI: POST-MAPPING--> /api/ratings
	* REQUIREMENT: Rating Body
	*/
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return new ResponseEntity<>(service.createRating(rating), HttpStatus.CREATED);
	}

	/*
	 * This endpoint will perform get all ratings from database and send
	 * URI: GET-MAPPING--> /api/ratings
	 * REQUIREMENT: Nothing
	 */
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		return ResponseEntity.ok(service.getAllRatings());
	}

	/*
	 * This endpoint will perform get Rating operation By Rating id
	 * URI: GET-MAPPING--> /api/ratings/{ratingId}
	 * REQUIREMENT: RatingId
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRating(@PathVariable("id") String ratingId){
		return ResponseEntity.ok(service.getRatingById(ratingId));
	}

	/*
	 * This endpoint will perform get rating operation By UserId
	 * URI: GET-MAPPING--> /api/ratings/user/{userId}
	 * REQUIREMENT: userId
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") String userId){
		return ResponseEntity.ok(service.getRatingByUserId(userId));
	}

	/*
	 * This endpoint will perform get rating operation by HotelId
	 * URI: GET-MAPPING--> /api/ratings/hotel/{hotelId}
	 * REQUIREMENT: HotelId
	 */
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable("hotelId") String hotelId){
		return ResponseEntity.ok(service.getRatingByHotelId(hotelId));
	}

	/*
	 * This endpoint will perform update rating operation usering
	 * rating id
	 * URI: PUT-MAPPING--> /api/ratings/{ratingId}
	 * REQUIREMENT: RatingId, Rating Body
	 */
	@PutMapping("/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratinId,
											   @RequestBody Rating rating){
		return ResponseEntity.ok(service.updateRatingByRatingId(ratinId,rating));
	}


	/*
	 * This endpoint will perform delete rating operation using
	 * rating id
	 * URI: DELETE-MAPPING--> /api/ratings/{ratingId}
	 * REQUIREMENT: RatingId
	 */
	@DeleteMapping("/{ratingId}")
	public ResponseEntity<ApiResponse> deleteRatingById(@PathVariable("ratingId") String ratingId){
		return ResponseEntity.ok(service.deleteRatingById(ratingId));
	}

	/*
	 * This endpoint will perform delete rating operation using
	 * user id
	 * URI: DELETE-MAPPING--> /api/ratings/user/{userId}
	 * REQUIREMENT: userId
	 */
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiResponse> deleteRatingByUserId(@PathVariable("userId") String userId){
		return ResponseEntity.ok(service.deleteRatingByUserId(userId));
	}

	/*
	 * This endpoint will perform delete rating operation using
	 * hotelId
	 * URI: DELETE-MAPPING--> /api/ratings/hotel/{hotelId}
	 * REQUIREMENT: hotelId
	 */
	@DeleteMapping("/hotel/{hotelId}")
	public ResponseEntity<ApiResponse> deleteRatingByHotelId(@PathVariable("hotelId") String hotelId){
		return ResponseEntity.ok(service.deleteRatingByHotelId(hotelId));
	}

}
