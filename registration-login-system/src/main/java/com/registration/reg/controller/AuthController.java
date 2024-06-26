package com.registration.reg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.registration.reg.dto.UserDto;
import com.registration.reg.entity.User;
import com.registration.reg.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	private UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	// handler method to handle home page request
	@GetMapping("/index")
	public String home() {
		return "index";
	}

	// handler method to handle user registration form request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";
	}
	
	//handler method to handle login request
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// handler method to handle user registration form submit request
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto,
								BindingResult result,
								Model model) {

		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already an account registered with this account");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}

		userService.saveUser(userDto);
		return "redirect:/register?successs";
	}

	// handler method to handle list of users
	@GetMapping("/users")
	public String users(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

}
