package com.online.taxi.passenger.request;

import lombok.Data;

@Data
public class UserAuthRequest {
	
	private String phoneNumber;
	
	private String code;
}
