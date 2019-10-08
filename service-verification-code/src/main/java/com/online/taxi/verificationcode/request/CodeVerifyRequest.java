package com.online.taxi.verificationcode.request;

import lombok.Data;

@Data
public class CodeVerifyRequest {
	
	private String phonenumber;
	
	private String code;
}
