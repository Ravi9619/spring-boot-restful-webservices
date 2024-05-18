package com.transaction.springboot.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.springboot.dto.OrderRequest;
import com.transaction.springboot.dto.OrderResponse;
import com.transaction.springboot.entity.Order;
import com.transaction.springboot.entity.Payment;
import com.transaction.springboot.exception.PaymentException;
import com.transaction.springboot.repository.OrderRepository;
import com.transaction.springboot.repository.PaymentRepository;
import com.transaction.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;
	private PaymentRepository paymentRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
		super();
		this.orderRepository = orderRepository;
		this.paymentRepository = paymentRepository;
	}

	@Override
	@Transactional
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		
		Order order = orderRequest.getOrder();
		order.setStatus("inProgress");
		order.setOrderTrackingNumber(UUID.randomUUID().toString());
		orderRepository.save(order);
		
		Payment payment = orderRequest.getPayment();
		
		if(!payment.getType().equals("DEBIT")) {
			throw new PaymentException("Payment card type do not support");
		}
		
		payment.setOrderId(order.getId());
		paymentRepository.save(payment);
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setMessage("SUCCESS");
		
		return orderResponse;	
	}
	
}
