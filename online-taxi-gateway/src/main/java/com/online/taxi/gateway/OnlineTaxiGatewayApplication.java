package com.online.taxi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class OnlineTaxiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTaxiGatewayApplication.class, args);
	}

}
