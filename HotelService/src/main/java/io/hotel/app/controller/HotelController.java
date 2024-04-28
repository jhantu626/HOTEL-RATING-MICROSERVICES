package io.hotel.app.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.hotel.app.models.Hotel;
import io.hotel.app.payload.ApiResponse;
import io.hotel.app.service.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	@Autowired
	private HotelServiceImpl service;

	/*
	*This endpoint will responsible to create Hotel
	* Requirements: Hotel Body(Hotel Name,Hotel Location,About the Hotel)
	* Method: POST-MAPPING
	* URI: /api/hotels
	*/
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return new ResponseEntity<>(service.createHotel(hotel), HttpStatus.CREATED);
	}


	/*
	 *This endpoint will responsible to give all hotel Details
	 * Requirements: Nothing
	 * Method: GET-MAPPING
	 * URI: /api/hotels
	 */
	@GetMapping
	@CircuitBreaker(name = "RATING_HOTEL_ALL_BREAKER",
			fallbackMethod = "ratingHotelAllFallback")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		return ResponseEntity.ok(service.getAllHotels());
	}
	//fallback method for RATING_HOTEL_ALL_BREAKER
	public ResponseEntity<List<Hotel>> ratingHotelAllFallback(Exception ex){
		return ResponseEntity.ok(List.of(
				Hotel.builder()
						.id("dummyId")
						.name("dummy")
						.location("dummy")
						.about("This service is down for internal server down")
						.build()
		));
	}
	/*
	 *This endpoint will responsible to give a specific hotel details with
	 * hotelId
	 * Requirements: HotelId
	 * Method: GET-MAPPING
	 * URI: /api/hotels/{hotelId}
	 */
	@GetMapping("/{id}")
	@CircuitBreaker(name = "RATING_HOTEL_BREAKER",
					fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<Hotel> getHotel(@PathVariable("id") String id){
		return ResponseEntity.ok(service.getHotel(id));
	}
	//fallback method for RATING_HOTEL_BREAKER
	public ResponseEntity<Hotel> ratingHotelFallback(String id,Exception ex){
		return ResponseEntity.ok(
				Hotel.builder()
						.id(id)
						.name("dummy")
						.location("dummy")
						.about("This service is down for internal server maintenance")
						.build()
		);
	}



	/*
	 *This endpoint will responsible to give a specific hotel details with
	 * hotelId
	 * Requirements: HotelId,Hotel Body(Hotel Name,Hotel Location,Hotel About)
	 * Method: PUT-MAPPING
	 * URI: /api/hotels/{hotelId}
	 */
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotelById(@PathVariable("hotelId") String hotelId,
												 @RequestBody Hotel hotel){
		return ResponseEntity.ok(service.updateHotelById(hotelId,hotel));
	}

	/*
	 *This endpoint will responsible to delete a specific hotel with
	 * hotelId
	 * Requirements: HotelId
	 * Method: DELETE-MAPPING
	 * URI: /api/hotels/{hotelId}
	 */
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotelById(@PathVariable("hotelId") String hotelId){
		return ResponseEntity.ok(service.deleteHotelById(hotelId));
	}


}
