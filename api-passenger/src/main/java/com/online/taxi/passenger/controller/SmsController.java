package com.online.taxi.passenger.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.online.taxi.common.constant.CommonStatus;
import com.online.taxi.common.dto.BaseResponse;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.util.PhoneUtil;
import com.online.taxi.passenger.dto.ShortMsgRequest;
import com.online.taxi.passenger.service.ShortMsgService;
import com.online.taxi.passenger.service.VerificationCodeService;

@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

	@Autowired
	private ShortMsgService shortMsgService;
	
	@Autowired
	private VerificationCodeService verificationCodeService;

//	@HystrixCommand(fallbackMethod = "sendFail")
	@PostMapping("/verify-code/send")
	public ResponseResult verifyCodeSend(@RequestBody ShortMsgRequest shortMsgRequest) {
		String phoneNumber = shortMsgRequest.getPhoneNumber();
		//校验手机号
		if(StringUtils.isBlank(phoneNumber)) {
			return ResponseResult.fail(CommonStatus.PHONENUMBER_EMPTY.getCode()	, CommonStatus.PHONENUMBER_EMPTY.PHONENUMBER_EMPTY.getValue());
		}
		Boolean phoneFlag = PhoneUtil.isPhone(phoneNumber);
		if(!phoneFlag) {
			return ResponseResult.fail(CommonStatus.PHONENUMBER_ERROR.getCode()	, CommonStatus.PHONENUMBER_ERROR.PHONENUMBER_EMPTY.getValue());
		}
		//获取验证码
		String code = verificationCodeService.getCode(phoneNumber);
		log.info("service-verification-code 返回的验证码："+code);
		shortMsgService.send(phoneNumber, code);
		
		return ResponseResult.success(null);
	}
	
	public ResponseResult sendFail(ShortMsgRequest shortMsgRequest) {
		//备用逻辑
		return ResponseResult.fail(-1, "熔断");
	}
}
