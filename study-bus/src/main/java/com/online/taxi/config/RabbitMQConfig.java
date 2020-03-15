package com.online.taxi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("direct.exc",true,false);
	}
	
	@Bean
	public Queue queue1() {
		return new Queue("q1",true,false,false);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue1()).to(directExchange()).with("r1");
	}
}
