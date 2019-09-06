package com.online.taxi.common.dto.sms;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class SmsSendRequest{

	private String[] receivers;
	private List<SmsTemplateDto> data;

	@Override
	public String toString() {
		return "SmsSendRequest [reveivers=" + Arrays.toString(receivers) + ", data=" + data + "]";
	}

}