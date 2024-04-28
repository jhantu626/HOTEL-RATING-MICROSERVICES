package io.app.external.service;

import io.app.models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(value = "HOTEL-SERVICE")
public interface HotelService {
	@GetMapping("/api/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
