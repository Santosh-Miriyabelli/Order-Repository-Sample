package com.sample.orderService.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sample.orderService.entity.OrderDetails;
import com.sample.orderService.exceptionhandler.OrderNotFoundException;
import com.sample.orderService.exceptionhandler.ResourceNotFoundException;
import com.sample.orderService.producer.RabbitMQProducer;
import com.sample.orderService.repository.OrderRepo;
import com.sample.orderService.request.OrderRequest;
import com.sample.orderService.response.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	

	@Autowired
	OrderRepo  orderRepo;
	
	@Autowired
	RabbitMQProducer producer;
	
	 
	
	public List<OrderDetails> listAllOrders(){
		log.info("Here 1");

		List<OrderDetails> orderList = orderRepo.findAll();

		if(orderList==null ) {
			throw new ResourceNotFoundException("NO ORDERS FOUND");
		}
		return orderList;
	}
	
	@Transactional
	public Response addOrder(OrderRequest request) {
		
		OrderDetails os = new OrderDetails(request.getCustomerName(),request.getOrderDate(),request.getTotalAmount(),request.getStatus());
		orderRepo.save(os);
		producer.sendMessageToQueue("Order is added to the Cart");
		
		return new Response(200,"order is Added with tracker id "+os.getId());
		
	}
	

	public OrderDetails getOrderDetails(long id) {
		log.info("getOrderDetails");
		
		return orderRepo.findById(id).orElseThrow(()	-> new OrderNotFoundException("Order not found with ID "+id));

	}
	
	@Transactional
	public OrderDetails updateOrderDetails(@RequestBody OrderRequest request){
		OrderDetails  orderDetaiils =orderRepo.findById(request.getId()).orElseThrow(()	-> new OrderNotFoundException("Order not found with ID "+request.getId()));
		if(request.getCustomerName()!=null && !request.getCustomerName().isEmpty())
			orderDetaiils.setCustomerName(request.getCustomerName());
		
		if(request.getOrderDate()!=null)
			orderDetaiils.setOrderDate(request.getOrderDate());
		
		if(request.getTotalAmount()!=0.0 )
			orderDetaiils.setTotalAmount(request.getTotalAmount());
		
		
		orderRepo.save(orderDetaiils);
		
		return orderDetaiils;
	}
	
	
	@Transactional
	public Response deleteOrderDetails(long id){
		OrderDetails  orderDetaiils =orderRepo.findById(id).orElseThrow(()	-> new OrderNotFoundException("Order not found with ID "+id));
		orderRepo.delete(orderDetaiils);
		
		return new Response(200,"order deleted");
	}
	
	
	
}
