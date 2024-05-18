package com.transaction.springboot.dto;

import com.transaction.springboot.entity.Order;
import com.transaction.springboot.entity.Payment;

public class OrderRequest {

	private Order order;
	private Payment payment;

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderRequest(Order order, Payment payment) {
		super();
		this.order = order;
		this.payment = payment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
