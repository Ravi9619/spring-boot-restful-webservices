package com.todomanagement.todo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todomanagement.todo.dto.TodoDTO;
import com.todomanagement.todo.entity.Todo;
import com.todomanagement.todo.exception.ResourceNotFoundException;
import com.todomanagement.todo.repository.TodoRepository;
import com.todomanagement.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
		super();
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TodoDTO addTodo(TodoDTO todoDTO) {

		// Convert TodoDto into Todo Jpa Entity
		
		Todo todo = modelMapper.map(todoDTO, Todo.class);

		Todo savedTodo = todoRepository.save(todo);

		// Convert Todo Jpa Entity into TodoDto
		
		TodoDTO saveTodoDTO = modelMapper.map(savedTodo, TodoDTO.class);

		return saveTodoDTO;

	}

	@Override
	public TodoDTO geTodo(Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));
		
		return modelMapper.map(todo, TodoDTO.class);
	}

	@Override
	public List<TodoDTO> getATodo() {
		
		List<Todo> allTodos = todoRepository.findAll();
		
		return allTodos.stream().map((todo) -> modelMapper.map(todo, TodoDTO.class))
				.collect(Collectors.toList());	
	}

	@Override
	public TodoDTO updateTodo(TodoDTO todoDTO, Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo Not Found with id: "+id));
		
		todo.setTitle(todoDTO.getTitle());
		todo.setDescription(todoDTO.getDescription());
		todo.setCompleted(todoDTO.isCompleted());
		
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDTO.class);
	}

	@Override
	public void deleteTodo(Long id) {
		
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id "+id));
		
		todoRepository.deleteById(id);
		
	}

	@Override
	public TodoDTO completeTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with "+id));
		
		todo.setCompleted(Boolean.TRUE);
		
		Todo savedTodo = todoRepository.save(todo);
		
		return modelMapper.map(savedTodo, TodoDTO.class);
	}
	
	@Override
	public TodoDTO inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with "+id));
		
		todo.setCompleted(Boolean.FALSE);
		
		Todo savedTodo = todoRepository.save(todo);
		
		return modelMapper.map(savedTodo, TodoDTO.class);
	}
}
