package com.online.taxi.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.one.service.RmOneService;

import io.seata.spring.annotation.GlobalTransactional;
/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/api2")
	public String test() {
		return "service-sms started";
	}
	
	@Autowired
	private RmOneService service;
	
	@GetMapping("/rm1")
	@GlobalTransactional(rollbackFor = Exception.class)
	public String rm1() {
		service.rm1();
		return "rm1 成功";
	}
}
