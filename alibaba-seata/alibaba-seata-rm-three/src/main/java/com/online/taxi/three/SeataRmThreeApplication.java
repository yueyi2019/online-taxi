package com.online.taxi.three;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author yueyi2019
 */
@EnableDiscoveryClient
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class SeataRmThreeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SeataRmThreeApplication.class, args);
	}

}
