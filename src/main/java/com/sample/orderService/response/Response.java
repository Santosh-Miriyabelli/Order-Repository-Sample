package com.sample.orderService.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
	
	private int statusCode;
    private String message;

}
