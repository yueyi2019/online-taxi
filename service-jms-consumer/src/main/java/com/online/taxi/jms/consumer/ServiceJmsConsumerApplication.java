package com.online.taxi.jms.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author yueyi2019
 */
@EnableEurekaClient
@SpringBootApplication
@EnableJms
public class ServiceJmsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceJmsConsumerApplication.class, args);
	}

}
