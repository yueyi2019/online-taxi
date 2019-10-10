package com.online.taxi.verificationcode.controller;

import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import com.online.taxi.common.constant.IdentityConstant;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
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
import com.online.taxi.common.dto.verificationcode.VerifyCodeResponse;
import com.online.taxi.verificationcode.constant.VerifyCodeConstant;
import com.online.taxi.verificationcode.request.CodeVerifyRequest;

@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 生成 验证码
	 * 生成对应身份，手机号 的验证码并保存redis，设置过期2分钟
	 * @param identity
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/generate/{identity}/{phoneNumber}")
	public ResponseResult<VerifyCodeResponse> generate(@PathVariable("identity") int identity ,@PathVariable ("phoneNumber") String phoneNumber) {
		log.info("身份类型："+identity+",手机号："+phoneNumber);
		//生成6位code
		String code = String.valueOf((int)((Math.random()*9+1)*100000));
		
		//生成redis key
		String keyPre = generateKeyPreByIdentity(identity);
		String key = keyPre + phoneNumber;
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
		log.info("校验验证码"+ JSONObject.fromObject(request));
		//获取手机号和验证码
		String phoneNumber = request.getPhoneNumber();
		int identity = request.getIdentity();
		String code = request.getCode();
		
		//生成redis key
		String keyPre = generateKeyPreByIdentity(identity);
		String key = keyPre + phoneNumber;
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

	private String generateKeyPreByIdentity(int identity){
		String keyPre = "";
		if (identity == IdentityConstant.PASSENGER){
			keyPre = VerifyCodeConstant.PASSENGER_LOGIN_KEY_PRE;
		}else if (identity == IdentityConstant.DRIVER){
			keyPre = VerifyCodeConstant.DRIVER_LOGIN_KEY_PRE;
		}
		return keyPre;
	}
	
}
