package com.todomanagement.todo.service;

import java.util.List;

import com.todomanagement.todo.dto.TodoDTO;

public interface TodoService {
	
	TodoDTO addTodo(TodoDTO todoDTO);
	
	TodoDTO geTodo(Long id);
	
	List<TodoDTO> getATodo();
	
	TodoDTO updateTodo(TodoDTO todoDTO, Long id);
	
	void deleteTodo(Long id);
	
	TodoDTO completeTodo(Long id);
	
	TodoDTO inCompleteTodo(Long id);

}
