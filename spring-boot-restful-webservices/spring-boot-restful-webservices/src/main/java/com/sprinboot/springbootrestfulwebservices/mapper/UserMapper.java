package com.sprinboot.springbootrestfulwebservices.mapper;

import com.sprinboot.springbootrestfulwebservices.dto.UserDto;
import com.sprinboot.springbootrestfulwebservices.entity.User;

public class UserMapper {
	
	public static UserDto mapToUserDto(User user) {
		
		//Convert User JPA to 
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
				);
		return userDto;
	}
	
	//Convert User Dto to User JPA Entity
	public static User mapToUser(UserDto userDto) {
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
				);
		return user;
	}

}
