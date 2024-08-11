package com.sample.orderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.orderService.entity.OrderDetails;
import com.sample.orderService.request.OrderRequest;
import com.sample.orderService.response.Response;
import com.sample.orderService.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/listOrders")
	public ResponseEntity<List<OrderDetails>> listAllOrders(){
		List<OrderDetails> ordersList =orderService.listAllOrders();
		
		return ResponseEntity.ok(ordersList);
		
	}
	
	
	@PostMapping("/addOrder")
	public ResponseEntity<Response> addOrder(@RequestBody OrderRequest request){
		Response ordersList =orderService.addOrder(request);
		
		return ResponseEntity.ok(ordersList);
		
	}
	
	@GetMapping("/orderDetails/{id}")
	public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable long id){
		OrderDetails ordersList =orderService.getOrderDetails(id);
		return ResponseEntity.ok(ordersList);
	}
	
	@PutMapping("/updateOrderDetails")
	public ResponseEntity<OrderDetails> updateOrderDetails(@RequestBody OrderRequest request){
		OrderDetails ordersDetails =orderService.updateOrderDetails(request);
		return ResponseEntity.ok(ordersDetails);
	}
	
	@DeleteMapping("/deleteOrderDetails/{id}")
	public ResponseEntity<Response> deleteOrderDetails(@PathVariable long id){
		Response response =orderService.deleteOrderDetails(id);
		return ResponseEntity.ok(response);
	}

}
