package com.online.taxi.verificationcode.controller;

import com.online.taxi.verificationcode.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.verificationcode.request.CodeVerifyRequest;
/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {
	
	@Autowired
	private VerifyCodeService verifyCodeService;

	/**
	 * 根据身份，手机号，生成验证码
	 * @param identity
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/generate/{identity}/{phoneNumber}")
	public ResponseResult generate(@PathVariable("identity") int identity ,@PathVariable ("phoneNumber") String phoneNumber) {
		log.info("身份类型："+identity+",手机号："+phoneNumber);
		return verifyCodeService.generate(identity,phoneNumber);
	}
	
	@PostMapping("/verify")
	public ResponseResult verify(@RequestBody CodeVerifyRequest request) {
		log.info("/verify-code/verify  request:"+ JSONObject.fromObject(request));
		//获取手机号和验证码
		String phoneNumber = request.getPhoneNumber();
		int identity = request.getIdentity();
		String code = request.getCode();
		
		return verifyCodeService.verify(identity,phoneNumber,code);
		
	}


	
}
