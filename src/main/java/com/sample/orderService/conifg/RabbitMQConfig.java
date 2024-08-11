package com.sample.orderService.conifg;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${rabbitmq.queue}")
	private String queue;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routeKey}")
	private String routeKey;
	
	@Bean
	public Queue getQueue() {
		return new Queue(queue);
		
	}
	
	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(getQueue()).to(getExchange()).with(routeKey);
	}
}
