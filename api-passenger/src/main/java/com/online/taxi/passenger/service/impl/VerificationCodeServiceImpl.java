package com.online.taxi.passenger.service.impl;

import com.online.taxi.common.constant.IdentityConstant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.verificationcode.VerifyCodeResponse;
import com.online.taxi.passenger.service.VerificationCodeService;

import javax.annotation.Resource;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String SERVICE_VERIFICATION_CODE_SERVICE = "service-verification-code";
	
	@Override
	public String getCode(String phoneNumber) {
		String url = "http://"+SERVICE_VERIFICATION_CODE_SERVICE+"/verify-code/generate/"+ IdentityConstant.PASSENGER+ "/" +phoneNumber;
		ResponseResult result = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<Object>(null,null),ResponseResult.class).getBody();

		if(result.getCode()==1) {
			JSONObject data = JSONObject.fromObject(result.getData().toString());
			VerifyCodeResponse response = (VerifyCodeResponse)JSONObject.toBean(data,VerifyCodeResponse.class);
			return response.getCode();
		}else {
			return "";
		}
	}

	@Override
	public String checkCode(String phoneNumber, String code) {
		return null;
	}

}
