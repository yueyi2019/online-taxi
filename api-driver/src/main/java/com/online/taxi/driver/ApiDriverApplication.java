package com.online.taxi.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.online.taxi.driver.annotation.ExcudeRibbonConfig;
import com.online.taxi.driver.ribbonconfig.RibbonConfiguration;

/**
 * @author yueyi2019
 */
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION,value=ExcudeRibbonConfig.class)
})
// 下面是让所有client都实现随机策略
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class ApiDriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDriverApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
