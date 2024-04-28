package io.app.service;

import io.app.models.User;
import io.app.payload.ApiResponse;

import java.util.List;

public interface UserService {
	public User saveUser(User user);
	public List<User> getAllUser();
	public User getUser(String userId);
	public User getUserByEmail(String email);
	public User updateUser(String userId,User user);
	public User updateUserByEmail(String email,User user);
	public ApiResponse deleteUserById(String userId);
	public ApiResponse deleteByEmail(String email);
}
