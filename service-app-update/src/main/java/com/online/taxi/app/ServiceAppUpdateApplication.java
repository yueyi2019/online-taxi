package com.online.taxi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class ServiceAppUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAppUpdateApplication.class, args);
	}

}
