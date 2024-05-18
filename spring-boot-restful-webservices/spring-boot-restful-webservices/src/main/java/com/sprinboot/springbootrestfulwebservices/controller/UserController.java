package com.sprinboot.springbootrestfulwebservices.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.sprinboot.springbootrestfulwebservices.dto.UserDto;
import com.sprinboot.springbootrestfulwebservices.entity.User;
import com.sprinboot.springbootrestfulwebservices.exception.ErrorDetails;
import com.sprinboot.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.sprinboot.springbootrestfulwebservices.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(
		name = "CRUD REST Api for User Resource",
		description = "CRUD Rest Api - Create User, Update User, Get User, Delete User"
		)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(
			summary = "Create User REST API",
			description = "API is used to save user in database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
	)
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto savedUser = userService.createUser(userDto);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary = "Get User REST API",
			description = "API is used to get user from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto userDto = userService.getUserByID(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> userDtos = userService.getAllUsers();

		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}
	
	
	@Operation(
			summary = "Update User REST API",
			description = "API is used to update user in database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user) {
		
		user.setId(id);
		UserDto updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Delete User REST API",
			description = "API is used to delete user in database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
	)
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("Deleted Successfully ",HttpStatus.OK);
	}
	
	//exceptionhandler annotation -> for specific exception
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException exception,
//																WebRequest webRequest) {
//		
//		ErrorDetails errorDetails = new ErrorDetails(
//				LocalDateTime.now(),
//				exception.getMessage(),
//				webRequest.getDescription(false),
//				"USER_NOT_FOUND"
//				);
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//		
//	}

}
