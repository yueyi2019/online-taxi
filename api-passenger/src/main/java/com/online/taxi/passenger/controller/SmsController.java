package com.online.taxi.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.passenger.dto.ShortMsgRequest;
import com.online.taxi.passenger.service.ShortMsgService;

@RestController
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private ShortMsgService shortMsgService;

	@HystrixCommand(fallbackMethod = "sendFail")
	@PostMapping("/send")
	public ResponseResult send(@RequestBody ShortMsgRequest shortMsgRequest) {
		String phonenumber = shortMsgRequest.getPhonenumber();
		// 还应有一个服务，获取验证码，临时先这么用
		String code = "123456";
		shortMsgService.send(phonenumber, code);
		return ResponseResult.success(null);
	}
	
	public ResponseResult sendFail(ShortMsgRequest shortMsgRequest) {

		return ResponseResult.fail(-1, "熔断");
	}
}
