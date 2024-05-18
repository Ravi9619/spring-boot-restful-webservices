package com.registration.reg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.reg.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
