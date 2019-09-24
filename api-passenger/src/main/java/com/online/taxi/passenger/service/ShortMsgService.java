package com.online.taxi.passenger.service;

import com.online.taxi.common.dto.ResponseResult;

public interface ShortMsgService {
	
	ResponseResult send(String phonenumber,String code);
	
	
}
