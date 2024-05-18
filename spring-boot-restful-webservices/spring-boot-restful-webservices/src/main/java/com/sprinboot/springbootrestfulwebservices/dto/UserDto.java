package com.sprinboot.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


@Schema(
		description = "User DTO Model Information"
		)
public class UserDto {
	
	private Long id;
	
	@NotEmpty(message = "First Name should not be null or empty")
	@Schema(description = "User first Name")
	private String firstName;
	
	@NotEmpty(message = "Last Name should not be null or empty")
	@Schema(description = "User last Name")
	private String lastName;
	
	@NotEmpty(message = "Email should not be null or empty")
	@Email(message = "Email should be valid")
	@Schema(description = "User Email")
	private String email;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
