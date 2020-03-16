package com.online.taxi.two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.two.service.RmTwoService;

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
	private RmTwoService service;
	
	@GetMapping("/rm2")
	public String rm1() {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		service.rm2();
		return "rm2 成功";
	}
}
