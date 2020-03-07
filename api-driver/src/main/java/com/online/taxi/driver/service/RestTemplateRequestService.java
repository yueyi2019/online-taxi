package com.online.taxi.driver.service;

import org.springframework.http.ResponseEntity;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;

public interface RestTemplateRequestService {
	
	ResponseResult smsSend(SmsSendRequest smsSendRequest);
	
	String grabOrder(int orderId, int driverId);

}
