package com.online.taxi.passenger.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.order.ForecastRequest;
import com.online.taxi.common.dto.order.ForecastResponse;
import com.online.taxi.passenger.feign.ServiceForecast;

@Configuration
public class ScheduleTest {
	
	@Autowired
	private ServiceForecast serviceForecast;

	public ResponseResult<ForecastResponse> forecast() {
		System.out.println("定时调用");
		ForecastRequest req = new ForecastRequest();
		req.setEndLatitude("el");
		req.setEndLongitude("el2");
		req.setStartLatitude("sl");
		req.setStartLongitude("sl2");
		ResponseResult<ForecastResponse> result = serviceForecast.forecast(req);
		
		return ResponseResult.success(result.getData());
	}
}
