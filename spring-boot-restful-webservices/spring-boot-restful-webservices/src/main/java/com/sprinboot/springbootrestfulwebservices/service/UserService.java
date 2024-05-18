package com.sprinboot.springbootrestfulwebservices.service;

import java.util.List;
import java.util.Optional;

import com.sprinboot.springbootrestfulwebservices.dto.UserDto;
import com.sprinboot.springbootrestfulwebservices.entity.User;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto getUserByID(Long userId);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto user);
	
	void deleteUser(Long userId);

}
