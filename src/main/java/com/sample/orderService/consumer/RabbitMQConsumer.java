package com.sample.orderService.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQConsumer {
	
	@RabbitListener(queues =  "${rabbitmq.queue}")
	public void consumer(String message) {
		log.info("Consumer recieved the message : " +  message);
	}

}
