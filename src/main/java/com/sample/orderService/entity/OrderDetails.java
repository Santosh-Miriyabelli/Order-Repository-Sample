package com.sample.orderService.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String customerName;
	
	private Date orderDate;
	
	private double totalAmount;
	
	private String status;

	public OrderDetails(String customerName, Date orderDate, double totalAmount, String status) {
		super();
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
	}
	
	

}
