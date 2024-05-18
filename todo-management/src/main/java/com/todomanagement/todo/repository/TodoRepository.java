package com.todomanagement.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todomanagement.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
