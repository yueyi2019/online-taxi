package com.online.taxi.passenger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.common.dto.sms.SmsTemplateDto;
import com.online.taxi.passenger.service.ShortMsgService;
import com.online.taxi.passenger.service.SmsClient;

import net.sf.json.JSONObject;

@Service
public class ShortMsgServiceImpl implements ShortMsgService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SmsClient smsClient;
	
	@Override
	public ResponseResult send(String phonenumber, String code) {
		System.out.println("手机号和验证码："+phonenumber+","+code);
		
		String url = "http://service-sms/send/alisms-template";
		SmsSendRequest smsSendRequest = new SmsSendRequest();
		String[] phonenumbers = new String[] {phonenumber};
		smsSendRequest.setReceivers(phonenumbers);
		
		List<SmsTemplateDto> data = new ArrayList<SmsTemplateDto>();
		SmsTemplateDto dto = new SmsTemplateDto();
		dto.setId("SMS_144145499");
		HashMap<String, Object> templateMap = new HashMap<>();
		templateMap.put("code", code);
		dto.setTemplateMap(templateMap);
		data.add(dto);
		
		smsSendRequest.setData(data);
		
		//ribbon调用
		ResponseEntity<ResponseResult> resultEntity = restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class);
		ResponseResult result = resultEntity.getBody();
		//feign调用
//		ResponseResult result = smsClient.sendSms(smsSendRequest);
		
		System.out.println(JSONObject.fromObject(result));
		return null;
	}

}
