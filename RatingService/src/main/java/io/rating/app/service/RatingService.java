package io.rating.app.service;

import io.rating.app.models.Rating;
import io.rating.app.payload.ApiResponse;

import java.util.List;

public interface RatingService {
	public Rating createRating(Rating rating);
	public List<Rating> getAllRatings();
	public Rating getRatingById(String id);
	public List<Rating> getRatingByUserId(String userId);
	public List<Rating> getRatingByHotelId(String hotelId);
	public Rating updateRatingByRatingId(String ratingId,Rating rating);
	public ApiResponse deleteRatingById(String ratingId);
	public ApiResponse deleteRatingByUserId(String userId);
	public ApiResponse deleteRatingByHotelId(String hotelId);

}