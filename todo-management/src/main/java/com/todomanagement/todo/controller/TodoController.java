package com.todomanagement.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todomanagement.todo.dto.TodoDTO;
import com.todomanagement.todo.service.TodoService;

@RestController
@RequestMapping("api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TodoDTO> addToDo(@RequestBody TodoDTO todoDTO) {
		
		TodoDTO saveTodoDTO = todoService.addTodo(todoDTO);
		
		return new ResponseEntity<>(saveTodoDTO, HttpStatus.CREATED);
	}
	
	//Build Get Todo
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDTO> getTodo(@PathVariable("id") Long todoId) {
		TodoDTO todoDTO = todoService.geTodo(todoId);
		
		return new ResponseEntity<>(todoDTO, HttpStatus.OK);
	}
	
	//Build Get All Todos
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDTO>> getAllToDo() {
		List<TodoDTO> allToDo = todoService.getATodo();
		
		return ResponseEntity.ok(allToDo);
	}
	
	//Build Update Todo
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO,
												@PathVariable Long id) {
		
		TodoDTO updateTodo = todoService.updateTodo(todoDTO, id);
		return ResponseEntity.ok(updateTodo);
		
	}
	
	//Build Delete Todo
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
		todoService.deleteTodo(id);
		
		return ResponseEntity.ok( "Todo Deleted Successfully: ");
	}
	
	//Build CompleteTodo
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDTO> completeTodo(@PathVariable Long id) {
		TodoDTO updateTodo = todoService.completeTodo(id);
		return ResponseEntity.ok(updateTodo);
	}
	
	//Build InCompleteTodo
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable Long id) {
		TodoDTO updateTodo = todoService.inCompleteTodo(id);
		return ResponseEntity.ok(updateTodo);
	}
}
