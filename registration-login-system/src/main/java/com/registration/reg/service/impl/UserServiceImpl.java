package com.registration.reg.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.reg.dto.UserDto;
import com.registration.reg.entity.Role;
import com.registration.reg.entity.User;
import com.registration.reg.repository.RoleRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
						RoleRepository roleRepository,
						PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getFirstName() + " " + userDto.getLastName());
		user.setEmail(userDto.getEmail());
		// encrypt password using spring security
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		Role role = roleRepository.findByName("ROLE_ADMIN");

		if (role == null) {
			role = checkRoleExist();
		}

		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}

	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream()
					.map((user) -> mapToUserDto(user))
					.collect(Collectors.toList());
	}

	private UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto();
		String[] str = user.getName().split(" ");
		userDto.setFirstName(str[0]);
		userDto.setLastName(str[1]);
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
