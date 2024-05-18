package com.transaction.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.springboot.dto.OrderRequest;
import com.transaction.springboot.dto.OrderResponse;
import com.transaction.springboot.service.OrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
		return ResponseEntity.ok(orderService.placeOrder(orderRequest));
	}
}
