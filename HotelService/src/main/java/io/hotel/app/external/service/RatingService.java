package io.hotel.app.external.service;

import io.hotel.app.models.Rating;
import io.hotel.app.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@FeignClient(value = "RATING-SERVICE")
public interface RatingService {
	@GetMapping("/api/ratings/hotel/{hotelId}")
	public List<Rating> getRatingsByHotelId(@PathVariable("hotelId") String hotelId);

	@DeleteMapping("api/ratings/hotel/{hotelId}")
	public ResponseEntity<ApiResponse> deleteRatingsByHotelId(@PathVariable("hotelId") String hotelId);

}
