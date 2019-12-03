package com.online.taxi.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.order.ForecastRequest;
import com.online.taxi.common.dto.order.ForecastResponse;
import com.online.taxi.passenger.feign.ServiceForecast;

/**
 * 
 * @author yueyi2019
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ServiceForecast serviceForecast;
	
	@PostMapping("/forecast")
	public ResponseResult<ForecastResponse> forecast(@RequestBody ForecastRequest forecastRequest) {
		
		ResponseResult<ForecastResponse> result = serviceForecast.forecast(forecastRequest);
		
		return ResponseResult.success(result.getData());
	}
}
