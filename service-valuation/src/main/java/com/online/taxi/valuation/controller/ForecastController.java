package com.online.taxi.valuation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.order.ForecastRequest;
import com.online.taxi.common.dto.order.ForecastResponse;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * 
 * @author yueyi2019
 *
 */
@Slf4j
@RestController
@RequestMapping("/forecast")
public class ForecastController {
	
	@Value("${server.port}")
	String port;
	
	@PostMapping("/single")
	public ResponseResult forecast(@RequestBody ForecastRequest forecastRequest) {
		JSONObject requestJson = JSONObject.fromObject(forecastRequest);
		log.info("计价参数："+port+" "+requestJson);
		
		ForecastResponse response = new ForecastResponse();
		
		Double price = 10.91;
		response.setPrice(price);
		return ResponseResult.success(response);
		
	}
	
}
