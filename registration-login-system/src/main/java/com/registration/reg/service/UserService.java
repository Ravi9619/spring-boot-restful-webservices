package com.registration.reg.service;

import java.util.List;

import com.registration.reg.dto.UserDto;
import com.registration.reg.entity.User;

public interface UserService {
	
	void saveUser(UserDto userDto);

	User findUserByEmail(String email);
	
	List<UserDto> findAllUsers();

}
