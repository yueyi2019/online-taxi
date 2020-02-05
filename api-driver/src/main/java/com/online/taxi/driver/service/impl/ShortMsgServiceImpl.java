package com.online.taxi.driver.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.common.dto.sms.SmsTemplateDto;
import com.online.taxi.driver.dto.ShortMsgRequest;
import com.online.taxi.driver.exception.BusinessException;
import com.online.taxi.driver.exception.HystrixIgnoreException;
import com.online.taxi.driver.service.ShortMsgService;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
/**
 * @author yueyi2019
 */
@Service
@Slf4j
public class ShortMsgServiceImpl implements ShortMsgService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	//比较好的Hystrix写在此处,我们为了演示方便，写在controller
//	@HystrixCommand(fallbackMethod = "sendFail")
	public ResponseResult send(String phoneNumber, String code) {
		
		// 下面是故意跑出异常代码
//		try {
//			int i = 1/0;
//		} catch (Exception e) {
//			// TODO: handle exception
////			throw new BusinessException("熔断忽略的异常，继承HystrixBadRequestException");
//			throw new HystrixIgnoreException("熔断忽略的异常，忽略属性设置");
//		}
		
		System.out.println("手机号和验证码："+phoneNumber+","+code);
		String http = "http://";
		String serviceName = "SERVICE-SMS";
		String uri = "/send/alisms-template";
		
		String url = http + serviceName + uri;
		SmsSendRequest smsSendRequest = new SmsSendRequest();
		String[] phoneNumbers = new String[] {phoneNumber};
		smsSendRequest.setReceivers(phoneNumbers);
		
		List<SmsTemplateDto> data = new ArrayList<SmsTemplateDto>();
		SmsTemplateDto dto = new SmsTemplateDto();
		dto.setId("SMS_144145499");
		int templateSize = 1;
		HashMap<String, Object> templateMap = new HashMap<String, Object>(templateSize);
		templateMap.put("code", code);
		dto.setTemplateMap(templateMap);
		data.add(dto);
		
		smsSendRequest.setData(data);
		
		//手写 ribbon调用
//		url = "";
//		ServiceInstance instance = loadBalance(serviceName);
//		url = http + instance.getHost()+":"+instance.getPort()+uri;
//		ResponseEntity<ResponseResult> resultEntity = restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class);
//		ResponseResult result = resultEntity.getBody();
		
		// 正常 ribbon调用
//		ResponseEntity<ResponseResult> resultEntity = restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class);
//		ResponseResult result = resultEntity.getBody();
		
		// 熔断restTemplate调用
		ResponseResult result = sendAlismsTemplateWithRestTemplate(url , smsSendRequest);
		
		
		System.out.println("调用短信服务返回的结果"+JSONObject.fromObject(result));
		return result;
	}
	
	public ResponseResult sendAlismsTemplateWithRestTemplate(String url , SmsSendRequest smsSendRequest) {
		
		ResponseEntity<ResponseResult> resultEntity = restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class);
		ResponseResult result = resultEntity.getBody();
		return result;
	}
	
	private ResponseResult sendFail(String phoneNumber, String code ,Throwable throwable) {
		log.info("异常信息："+throwable);
		//备用逻辑
		return ResponseResult.fail(-1, "熔断");
	}
	
	/*
	 *	下面代码手写ribbon 
	 */
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	private ServiceInstance loadBalance(String serviceName) {
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
		ServiceInstance instance = instances.get(new Random().nextInt(instances.size()));
		log.info("负载均衡 选出来的ip："+instance.getHost()+",端口："+instance.getPort());
		
		Map<String, String> metadata = instance.getMetadata();
		
		return instance;
	}
	
	/*
	 *	上面代码手写ribbon 
	 */
	
}
