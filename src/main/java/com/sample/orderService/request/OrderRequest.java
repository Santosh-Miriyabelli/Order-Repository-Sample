package com.sample.orderService.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {
	
	private long id;
	private String customerName;
	private Date orderDate;
	private double totalAmount;
	private String status;
	
	
	public OrderRequest(String customerName, Date orderDate, double totalAmount, String status) {
		this.customerName=customerName;
		this.orderDate=orderDate;
		this.totalAmount=totalAmount;
		this.status=status;
	}


	

}
