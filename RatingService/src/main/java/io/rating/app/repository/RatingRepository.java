package io.rating.app.repository;

import io.rating.app.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {
	public List<Rating> findByUserId(String userId);
	public List<Rating> findByHotelId(String hotelId);
}
