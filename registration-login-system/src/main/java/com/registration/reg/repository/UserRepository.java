package com.registration.reg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.reg.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
