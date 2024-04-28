package io.rating.app.service.impl;

import io.rating.app.exception.ResourceNotFoundException;
import io.rating.app.models.Rating;
import io.rating.app.payload.ApiResponse;
import io.rating.app.repository.RatingRepository;
import io.rating.app.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository repository;
	@Override
	public Rating createRating(Rating rating) {
		String uid= UUID.randomUUID().toString();
		rating.setRatingId(uid);
		return repository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return repository.findAll();
	}

	@Override
	public Rating getRatingById(String id) {
		return repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNotFound By Id "+id));
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repository.findByHotelId(hotelId);
	}

	@Override
	public Rating updateRatingByRatingId(String ratingId, Rating rating) {
		Rating dataRatng=repository.findById(ratingId)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNotFound By Id "+ratingId));
		dataRatng.setFeedback(rating.getFeedback());
		dataRatng.setRating(rating.getRating());
		return repository.save(dataRatng);
	}

	@Override
	public ApiResponse deleteRatingById(String ratingId) {
		Rating dataRatng=repository.findById(ratingId)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNotFound By Id "+ratingId));
		repository.delete(dataRatng);
		return ApiResponse.builder()
				.msg("Rating deleted successfully!")
				.status(HttpStatus.OK)
				.build();
	}

	@Override
	public ApiResponse deleteRatingByUserId(String userId) {
		List<Rating> ratings=repository.findByUserId(userId);
		repository.deleteAll(ratings);
		return ApiResponse.builder()
				.msg("Ratings Deleted Successfully")
				.status(HttpStatus.OK)
				.build();
	}

	@Override
	public ApiResponse deleteRatingByHotelId(String hotelId) {
		List<Rating> ratings=repository.findByHotelId(hotelId);
		repository.deleteAll(ratings);
		return ApiResponse.builder()
				.msg("Ratings Deleted Successfully")
				.status(HttpStatus.OK)
				.build();
	}

}
