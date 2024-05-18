package com.transaction.springboot.dto;

public class OrderResponse {

	private String orderTrackingNumber;
	private String status;
	private String message;

	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResponse(String orderTrackingNumber, String status, String message) {
		super();
		this.orderTrackingNumber = orderTrackingNumber;
		this.status = status;
		this.message = message;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
