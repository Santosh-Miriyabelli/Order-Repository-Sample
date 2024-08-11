package com.sample.orderService.exceptionhandler;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(String message) {
        super(message);
    }
}
