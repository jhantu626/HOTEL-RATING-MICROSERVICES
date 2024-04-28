package io.app.external.service;

import io.app.models.Rating;
import io.app.payload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@FeignClient(value = "RATING-SERVICE")
public interface RatingService {
	@GetMapping("/api/ratings/user/{userId}")
	public List<Rating> getAllRatingsByUserId(@PathVariable("userId") String userId);
	@DeleteMapping("/api/ratings/user/{userId}")
	public ResponseEntity<ApiResponse> deleteRatingByUserId(@PathVariable("userId") String userId);
}
