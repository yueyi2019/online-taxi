package com.online.taxi.order.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class ServiceOrderDispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderDispatchApplication.class, args);
	}

}
