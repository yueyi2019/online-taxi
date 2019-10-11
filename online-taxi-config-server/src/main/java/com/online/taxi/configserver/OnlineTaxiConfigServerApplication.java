package com.online.taxi.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
 * @author yueyi2019
 */
@EnableConfigServer
@SpringBootApplication
public class OnlineTaxiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTaxiConfigServerApplication.class, args);
	}

}
