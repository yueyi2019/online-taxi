package com.online.taxi.passenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.dto.ResponseResult;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/api1")
	public ResponseResult send() {
		JSONObject result = new JSONObject();
		result.put("api-passenger", "乘客api接口服务");
		
		return ResponseResult.success(result);
	}
	
	
}
