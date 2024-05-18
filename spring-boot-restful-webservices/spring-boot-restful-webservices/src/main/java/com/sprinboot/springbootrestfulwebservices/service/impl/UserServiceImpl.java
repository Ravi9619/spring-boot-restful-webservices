package com.sprinboot.springbootrestfulwebservices.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.springbootrestfulwebservices.dto.UserDto;
import com.sprinboot.springbootrestfulwebservices.entity.User;
import com.sprinboot.springbootrestfulwebservices.exception.EmailAlreadyExistException;
import com.sprinboot.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.sprinboot.springbootrestfulwebservices.mapper.AutoUserMapper;
import com.sprinboot.springbootrestfulwebservices.mapper.UserMapper;
import com.sprinboot.springbootrestfulwebservices.repository.UserRepository;
import com.sprinboot.springbootrestfulwebservices.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public UserServiceImpl(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Convert UserDto into User JPA Entity
		//User user = UserMapper.mapToUser(userDto);
		
		//User user = modelMapper.map(userDto, User.class);
		
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistException("Email Already Exists for User");
		}
		
		User user = AutoUserMapper.MAPPER.mapUser(userDto);
		
		User savedUser = userRepository.save(user);
		
		//Convert User JPA Entity to UserDto
		//UserDto saveUserDto = UserMapper.mapToUserDto(savedUser);
		
		//UserDto saveUserDto = modelMapper.map(savedUser, UserDto.class);
		
		UserDto saveUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		
		return saveUserDto;
	}

	@Override
	public UserDto getUserByID(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User","id", userId)
		);
		//User user = optionalUser.get();
		
		//return UserMapper.mapToUserDto(user);
		
		//return modelMapper.map(user,UserDto.class);
		
		return AutoUserMapper.MAPPER.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		//return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		
		//return allUsers.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return allUsers.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		//User existingUser = userRepository.findById(user.getId()).get();
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User","id", user.getId())
		);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail()); 
		User updatedUser = userRepository.save(existingUser);
		//return UserMapper.mapToUserDto(updatedUser);
		
		//return modelMapper.map(updatedUser, UserDto.class);
		
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User","id", userId)
		);
		userRepository.deleteById(userId);
	}

}
