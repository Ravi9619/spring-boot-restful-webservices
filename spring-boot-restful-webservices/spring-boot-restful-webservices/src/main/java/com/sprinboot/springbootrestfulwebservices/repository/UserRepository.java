package com.sprinboot.springbootrestfulwebservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprinboot.springbootrestfulwebservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//Custom QueryMethod
	Optional<User> findByEmail(String email);
}
