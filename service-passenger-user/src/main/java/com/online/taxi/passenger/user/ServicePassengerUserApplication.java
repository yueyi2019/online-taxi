package com.online.taxi.passenger.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @author yueyi2019
 */
@EnableEurekaClient
@SpringBootApplication
public class ServicePassengerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePassengerUserApplication.class, args);
	}

}
