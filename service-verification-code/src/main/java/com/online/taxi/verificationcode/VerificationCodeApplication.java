package com.online.taxi.verificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VerificationCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerificationCodeApplication.class, args);
	}

}
