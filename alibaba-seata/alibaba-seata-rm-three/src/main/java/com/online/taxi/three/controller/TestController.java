package com.online.taxi.three.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.three.service.RmThreeService;

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
	private RmThreeService service;
	
	@GetMapping("/rm3")
	public String rm1() {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		service.rm3();
//		int i = 1/0;
		return "rm3成功";
	}
}
