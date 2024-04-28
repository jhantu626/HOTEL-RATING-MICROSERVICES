package io.app.service.impl;

import io.app.exception.ResourceNotFoundException;
import io.app.external.service.HotelService;
import io.app.external.service.RatingService;
import io.app.models.Hotel;
import io.app.models.Rating;
import io.app.models.User;
import io.app.payload.ApiResponse;
import io.app.repository.UserRepository;
import io.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final RestTemplate restTemplate;
	private final HotelService hotelService;
	private final RatingService ratingService;
	@Override
	public User saveUser(User user) {
		String id=UUID.randomUUID().toString();
		user.setId(id);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users=repository.findAll();
		List<User> finalUsersList=users.stream().map((user)->{
			List<Rating> ratings=ratingService.getAllRatingsByUserId(user.getId());
			List<Rating> finalRatingsList=ratings.stream().map(t->{
				Hotel hotel=hotelService.getHotel(t.getHotelId());
				t.setHotel(hotel);
				return t;
			}).collect(Collectors.toList());
			user.setRatings(finalRatingsList);
			return user;
		}).collect(Collectors.toList());
		return finalUsersList;
	}

	@Override
	public User getUser(String userId) {
		User user=repository.findById(userId).orElseThrow(()->new ResourceNotFoundException("ResourceNotFound By Id "+userId));
		List<Rating> list =ratingService.getAllRatingsByUserId(user.getId());
		List<Rating> finalList=list.stream().map(t->{
			Hotel hotel=hotelService.getHotel(t.getHotelId());
			t.setHotel(hotel);
			return t;
		}).collect(Collectors.toList());
		user.setRatings(finalList);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User dataUser=repository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By Email "+email));
		List<Rating> ratings=ratingService.getAllRatingsByUserId(dataUser.getId())
				.stream().map(t->{
					Hotel hotel=hotelService.getHotel(t.getHotelId());
					t.setHotel(hotel);
					return t;
				}).collect(Collectors.toList());
		dataUser.setRatings(ratings);
		return dataUser;
	}

	@Override
	public User updateUser(String userId, User user) {
		User dataUser=repository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By Id "+userId));
		dataUser.setName(user.getName());
		dataUser.setAbout(user.getAbout());
		return repository.save(dataUser);
	}

	@Override
	public User updateUserByEmail(String email, User user) {
		User dataUser=repository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By Email "+email));
		dataUser.setAbout(user.getAbout());
		dataUser.setName(user.getName());
		return repository.save(dataUser);
	}

	@Override
	public ApiResponse deleteUserById(String userId) {
		User dataUser=repository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By Id "+userId));
		ratingService.deleteRatingByUserId(dataUser.getId());
		repository.delete(dataUser);
		return ApiResponse.builder()
				.msg("Deleted Successfully!")
				.status(HttpStatus.OK)
				.success(true)
				.build();
	}

	@Override
	public ApiResponse deleteByEmail(String email) {
		User dataUser=repository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Not Found By Email "+email));
		ratingService.deleteRatingByUserId(dataUser.getId());
		repository.delete(dataUser);
		return ApiResponse.builder()
				.msg("Deleted Successfully!")
				.status(HttpStatus.OK)
				.success(true)
				.build();
	}
}





