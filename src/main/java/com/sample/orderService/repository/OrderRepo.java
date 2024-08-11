package com.sample.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.orderService.entity.OrderDetails;

@Repository
public interface OrderRepo extends JpaRepository	<OrderDetails, Long>{

}
