package com.online.taxi.jms.provider;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @author yueyi2019
 */
@EnableEurekaClient
@SpringBootApplication
@EnableJms
public class ServiceJmsProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceJmsProviderApplication.class, args);
	}

	@Bean
	ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory();
	}

	@Bean
	JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setPriority(999);
		return jmsTemplate;
	}

	@Bean(value="jmsMessagingTemplate")
	JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
		JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
		return messagingTemplate;
	}
}
