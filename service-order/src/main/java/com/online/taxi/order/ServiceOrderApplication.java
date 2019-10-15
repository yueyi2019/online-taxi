package com.online.taxi.order;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yueyi2019
 */
@EnableEurekaClient
@SpringBootApplication
@EnableAsync
public class ServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApplication.class, args);
	}

	@Bean
	public RedissonClient redissonClient(){
		Config config = new Config();

		config. useSingleServer().setAddress("127.0.0.1:6379");

		RedissonClient redisson = Redisson.create(config);
		return  redisson;
	}

}
