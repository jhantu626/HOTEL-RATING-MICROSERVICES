package io.hotel.app.service;

import io.hotel.app.models.Hotel;
import io.hotel.app.payload.ApiResponse;

import java.util.List;

public interface HotelService {
	public Hotel createHotel(Hotel hotel);
	public Hotel getHotel(String id);
	public List<Hotel> getAllHotels();
	public Hotel updateHotelById(String hotelId,Hotel hotel);
	public ApiResponse deleteHotelById(String hotelId);
}




