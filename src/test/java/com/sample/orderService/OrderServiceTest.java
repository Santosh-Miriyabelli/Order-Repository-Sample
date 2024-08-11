package com.sample.orderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.sample.orderService.entity.OrderDetails;
import com.sample.orderService.exceptionhandler.OrderNotFoundException;
import com.sample.orderService.producer.RabbitMQProducer;
import com.sample.orderService.repository.OrderRepo;
import com.sample.orderService.request.OrderRequest;
import com.sample.orderService.response.Response;
import com.sample.orderService.service.OrderService;

public class OrderServiceTest {
	
	@Mock
    private OrderRepo orderRepo;
	
	@Mock
	RabbitMQProducer producer;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllOrders_Success() {
        List<OrderDetails> orders = Arrays.asList(
            new OrderDetails(1, "John Doe", new Date(), 100.0, "Pending"),
            new OrderDetails(2, "Jane Doe", new Date(), 200.0, "Completed")
        );
        when(orderRepo.findAll()).thenReturn(orders);

        List<OrderDetails> result = orderService.listAllOrders();
        assertEquals(2, result.size());
    }

   

    @Test
    public void testAddOrder_Success() {
        OrderRequest request = new OrderRequest("John Doe", new Date(), 100.0, "Pending");
        OrderDetails order = new OrderDetails(1, "John Doe", new Date(), 100.0, "Pending");
        when(orderRepo.save(any(OrderDetails.class))).thenReturn(order);
       // when(producer.sendMessageToQueue(anyString())).

        Response response = orderService.addOrder(request);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getMessage().contains("order is Added with tracker id "));
    }

    @Test
    public void testGetOrderDetails_Success() {
        OrderDetails order = new OrderDetails(1, "John Doe", new Date(), 100.0, "Pending");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));

        OrderDetails result = orderService.getOrderDetails(1L);
        assertEquals("John Doe", result.getCustomerName());
    }

    @Test
    public void testGetOrderDetails_OrderNotFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderDetails(1L);
        });

        assertEquals("Order not found with ID 1", exception.getMessage());
    }

    @Test
    public void testUpdateOrderDetails_Success() {
        OrderRequest request = new OrderRequest();
        request.setId(1);
        request.setCustomerName("John Doe");
        request.setOrderDate(new Date());
        request.setTotalAmount(100);
        request.setStatus("pending");
        OrderDetails order = new OrderDetails(1, "John Doe", new Date(), 100.0, "Pending");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepo.save(any(OrderDetails.class))).thenReturn(order);

        OrderDetails result = orderService.updateOrderDetails(request);
        assertEquals(100.0, result.getTotalAmount());
        
    }

    @Test
    public void testUpdateOrderDetails_OrderNotFound() {
        OrderRequest request = new OrderRequest();
        request.setId(1);
        request.setCustomerName("John Doe");
        request.setOrderDate(new Date());
        request.setTotalAmount(150.0);
        request.setStatus("Completed");
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrderDetails(request);
        });

        assertEquals("Order not found with ID 1", exception.getMessage());
    }

    @Test
    public void testDeleteOrderDetails_Success() {
        OrderDetails order = new OrderDetails(1, "John Doe", new Date(), 100.0, "Pending");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));

        Response response = orderService.deleteOrderDetails(1L);
        assertEquals(200, response.getStatusCode());
        assertEquals("order deleted", response.getMessage());
    }

    @Test
    public void testDeleteOrderDetails_OrderNotFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteOrderDetails(1L);
        });

        assertEquals("Order not found with ID 1", exception.getMessage());
    }

}
