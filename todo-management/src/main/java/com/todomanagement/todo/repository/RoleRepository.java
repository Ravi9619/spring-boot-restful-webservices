package com.todomanagement.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todomanagement.todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	

}
