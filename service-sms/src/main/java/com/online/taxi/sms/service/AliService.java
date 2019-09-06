package com.online.taxi.sms.service;

import com.online.taxi.common.dto.sms.SmsSendRequest;

public interface AliService {

	public int sendSms(SmsSendRequest request);
}
