package com.online.taxi.passenger.feign.config;

import org.springframework.context.annotation.Bean;

import feign.auth.BasicAuthRequestInterceptor;

public class FeignAuthConfiguration {
	
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("root", "root");
	}
}