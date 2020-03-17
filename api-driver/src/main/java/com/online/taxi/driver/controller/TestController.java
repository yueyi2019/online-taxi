package com.online.taxi.driver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.driver.request.HelloRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam String name, @RequestParam  Integer age) {
		log.info("api driver 参数：name：{},age:{}",name,age);
		return "api-driver-hello:"+port;
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "api-driver-admin:"+port;
	}
	
	@RequestMapping("/token")
	public String cookie(HttpServletRequest req) {
		String token = req.getHeader("token");
		System.out.println("token:"+token);
		
		return "api-driver-token:"+token;
	}
	
	@RequestMapping("/auth")
	public String auth(HttpServletRequest req) {
		String token = req.getHeader("token");
		System.out.println("auth token:"+token);
		
		return "api-driver-auth:"+token;
	}
	
}
