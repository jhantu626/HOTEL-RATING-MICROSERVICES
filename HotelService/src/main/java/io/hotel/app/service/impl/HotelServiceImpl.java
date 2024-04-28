package io.hotel.app.service.impl;

import io.hotel.app.exception.ResourceNotFoundException;
import io.hotel.app.external.service.RatingService;
import io.hotel.app.models.Hotel;
import io.hotel.app.models.Rating;
import io.hotel.app.payload.ApiResponse;
import io.hotel.app.repository.HotelRepository;
import io.hotel.app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository repository;
	@Autowired
	private RatingService ratingService;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String id= UUID.randomUUID().toString();
		hotel.setId(id);
		return repository.save(hotel);
	}

	@Override
	public Hotel getHotel(String id) {
		Hotel hotel=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By ID "+id));
		List<Rating> ratings=ratingService.getRatingsByHotelId(hotel.getId());
		hotel.setRatings(ratings);
		return hotel;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> hotels= repository.findAll()
				.stream().map((hotel)->{
					List<Rating> ratings=ratingService.getRatingsByHotelId(hotel.getId());
					hotel.setRatings(ratings);
					return hotel;
				}).collect(Collectors.toList());
		return hotels;
	}

	@Override
	public Hotel updateHotelById(String hotelId, Hotel hotel) {
		Hotel dataHotel=repository.findById(hotelId)
				.orElseThrow(()->new ResourceNotFoundException("Hotel Not Found By ID: "+hotelId));
		dataHotel.setName(hotel.getName()!=null?hotel.getName():dataHotel.getName());
		dataHotel.setLocation(hotel.getLocation()!=null?hotel.getLocation():dataHotel.getLocation());
		dataHotel.setAbout(hotel.getAbout()!=null?hotel.getAbout():dataHotel.getAbout());
		return repository.save(dataHotel);
	}

	@Override
	public ApiResponse deleteHotelById(String hotelId) {
		Hotel hotel=repository.findById(hotelId)
				.orElseThrow(()->new ResourceNotFoundException("Hotel Not Found By ID: "+hotelId));
		ratingService.deleteRatingsByHotelId(hotel.getId());
		repository.delete(hotel);
		return ApiResponse.builder()
				.msg("Hotel Deleted Successfully")
				.status(HttpStatus.OK)
				.build();
	}
}
