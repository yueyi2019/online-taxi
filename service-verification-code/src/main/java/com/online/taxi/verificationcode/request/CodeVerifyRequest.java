package com.online.taxi.verificationcode.request;

import lombok.Data;

@Data
public class CodeVerifyRequest {

	private int identity;

	private String phoneNumber;
	
	private String code;
}
