package com.online.taxi.passenger.fallback;

import org.springframework.stereotype.Component;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.passenger.service.SmsClient;

@Component
public class SmsClientFallback implements SmsClient {

	@Override
	public ResponseResult sendSms(SmsSendRequest smsSendRequest) {
		System.out.println("不好意思，我熔断了");
		return null;
	}

}
