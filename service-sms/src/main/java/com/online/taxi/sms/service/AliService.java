package com.online.taxi.sms.service;

import com.online.taxi.common.dto.sms.SmsSendRequest;

/**
 * @author yueyi2019
 */
public interface AliService {
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	public int sendSms(SmsSendRequest request);
}
