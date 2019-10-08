package com.online.taxi.verificationcode.controller;

import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.constant.CommonStatus;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.verificationcode.constant.VerifyCodeConstant;
import com.online.taxi.verificationcode.request.CodeVerifyRequest;
import com.online.taxi.verificationcode.response.VerifyCodeResponse;

@RestController
@RequestMapping("/verify-code")
public class VerifyCodeController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@GetMapping("/generate/{phonenumber}")
	public ResponseResult<VerifyCodeResponse> generate(@PathVariable ("phonenumber") String phonenumber) {
		//生成6位code
		String code = String.valueOf((int)((Math.random()*9+1)*100000));
		
		//生成redis key
		String key = VerifyCodeConstant.PASSENGER_LOGIN_KEY+phonenumber;
		
		//存redis，2分钟过期
		BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
		codeRedis.set(code);
		codeRedis.expire(120, TimeUnit.SECONDS);
		
		//返回
		VerifyCodeResponse result = new VerifyCodeResponse();
		result.setCode(code);
		return ResponseResult.success(result); 
	}
	
	@PostMapping("/verify")
	public ResponseResult verify(@RequestBody CodeVerifyRequest request) {
		//获取手机号和验证码
		String phonenumber = request.getPhonenumber();
		
		String code = request.getCode();
		
		//生成redis key
		String key = VerifyCodeConstant.PASSENGER_LOGIN_KEY+phonenumber;
		BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
		String redisCode = codeRedis.get();
		
		if(StringUtils.isNotBlank(code) 
				&& StringUtils.isNotBlank(redisCode) 
				&& code.trim().equals(redisCode.trim())) {
			return ResponseResult.success(null);
		}else {
			return ResponseResult.fail(CommonStatus.VERIFY_CODE_ERROR.getCode(),CommonStatus.VERIFY_CODE_ERROR.getValue());
		}
		
	}
	
	
}
