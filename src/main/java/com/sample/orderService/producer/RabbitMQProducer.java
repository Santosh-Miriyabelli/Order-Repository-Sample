package com.sample.orderService.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQProducer {
	
	@Value("${rabbitmq.queue}")
	private String queue;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routeKey}")
	private String routeKey;

	private RabbitTemplate rabbitTemplate;
	
	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate =rabbitTemplate;
	}
	
	
	public void sendMessageToQueue(String message) {
		log.info("Sending the message to Queue: "+message);
		rabbitTemplate.convertAndSend(exchange, routeKey, message);
	}
	
}
