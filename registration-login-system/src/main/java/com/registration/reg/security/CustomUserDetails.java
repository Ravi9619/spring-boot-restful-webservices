package com.registration.reg.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.registration.reg.entity.User;
import com.registration.reg.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetails(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		 
		User user = userRepository.findByEmail(usernameOrEmail); 
	        if(user != null){
	            return new org.springframework.security.core.userdetails.User(user.getEmail()
	                    , user.getPassword(),
	                    user.getRoles().stream()
	                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
	                            .collect(Collectors.toList()));
	        }else {
	            throw new UsernameNotFoundException("Invalid email or password");
	        }
	}
}
