package com.transaction.springboot.service;

import com.transaction.springboot.dto.OrderRequest;
import com.transaction.springboot.dto.OrderResponse;

public interface OrderService {
	
	OrderResponse placeOrder(OrderRequest orderRequest);
}
