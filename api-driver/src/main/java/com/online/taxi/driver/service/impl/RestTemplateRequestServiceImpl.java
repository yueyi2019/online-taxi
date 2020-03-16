package com.online.taxi.driver.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.driver.constant.HttpUrlConstants;
import com.online.taxi.driver.exception.BusinessException;
import com.online.taxi.driver.exception.HystrixIgnoreException;
import com.online.taxi.driver.service.RestTemplateRequestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestTemplateRequestServiceImpl implements RestTemplateRequestService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
//	@HystrixCommand(fallbackMethod = "sendFail")
//	@HystrixCommand(fallbackMethod = "sendFail",ignoreExceptions = {HystrixIgnoreException.class},
//	commandProperties = {
//			@HystrixProperty(name = "fallback.enabled",value = "true"),
//			@HystrixProperty(name = "circuitBreaker.forceOpen",value = "false")
//	})
	public ResponseResult smsSend(SmsSendRequest smsSendRequest) {
		
		// 下面是故意跑出异常代码
//		try {
//			int i = 1/0;
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new BusinessException("熔断忽略的异常，继承HystrixBadRequestException");
////			throw new HystrixIgnoreException("熔断忽略的异常，忽略属性设置");
//		}
		
		String url = HttpUrlConstants.SERVICE_SMS_URL + "/send/alisms-template";
		return restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class).getBody();
	}
	
//	private ResponseResult sendFail(SmsSendRequest smsSendRequest) {
//		//备用逻辑
//		return ResponseResult.fail(-3, "resttemplate熔断");
//	}
	
	private ResponseResult sendFail(SmsSendRequest smsSendRequest ,Throwable throwable) {
		log.info("异常信息："+throwable);
		//备用逻辑
		
		return ResponseResult.fail(-3, "restTemplate熔断");
	}
	
	// 都是请求底层服务的。
	
	public String grabOrder(int orderId, int driverId) {
		
		String url = HttpUrlConstants.SERVICE_ORDER_URL + "/grab/do/"+orderId+"?driverId="+driverId;
		
		return restTemplate.getForEntity(url, String.class).getBody();
	}

}
