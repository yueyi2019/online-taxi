package com.online.taxi.passenger.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;

@FeignClient(name = "service-sms")
public interface SmsClient {

	@RequestMapping(value="/send/alisms-template", method = RequestMethod.POST)
	public ResponseResult sendSms(@RequestBody SmsSendRequest smsSendRequest);
}
