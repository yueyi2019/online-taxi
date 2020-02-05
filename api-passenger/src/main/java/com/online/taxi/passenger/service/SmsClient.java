package com.online.taxi.passenger.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.passenger.fallback.SmsClientFallback;
/**
 * @author yueyi2019
 */
@FeignClient(name = "service-sms",fallback = SmsClientFallback.class)
//@FeignClient(name = "service-sms")
public interface SmsClient {
	/**
	 * 按照短信模板发送验证码
	 * @param smsSendRequest
	 * @return
	 */
	@RequestMapping(value="/send/alisms-template", method = RequestMethod.POST)
	public ResponseResult sendSms(@RequestBody SmsSendRequest smsSendRequest);
}
