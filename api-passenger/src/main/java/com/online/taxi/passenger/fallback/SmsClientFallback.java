package com.online.taxi.passenger.fallback;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.passenger.service.SmsClient;
/**
 * @author yueyi2019
 */
@Component
public class SmsClientFallback implements SmsClient {

//	@Autowired
//	private StringRedisTemplate redisTemplate;
	
	@Override
	public ResponseResult sendSms(SmsSendRequest smsSendRequest) {
		System.out.println("不好意思，我熔断了");
		
		String key = "service-sms";
//		String noticeString = redisTemplate.opsForValue().get(key);
//		if(StringUtils.isBlank(noticeString)) {
			//发送短信，或者调用电话接口
			System.out.println("通知别人我熔断了");
//			redisTemplate.opsForValue().set(key, "notice", 30, TimeUnit.SECONDS);
			
//		}else {
//			System.out.println("通知过了，我先不通知了");
//		}
		
		return null;
	}

}
