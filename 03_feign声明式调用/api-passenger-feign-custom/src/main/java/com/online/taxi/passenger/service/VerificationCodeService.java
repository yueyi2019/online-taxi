package com.online.taxi.passenger.service;

import com.online.taxi.common.dto.ResponseResult;
/**
 * 	验证码服务
 * @author yueyi2019
 *
 */
public interface VerificationCodeService {
	/**
	 * 	获取验证码
	 * @param phonenumber
	 * @return
	 */
	public String getCode(String phonenumber);
	
	/**
	 * 	校验验证码
	 * @param phonenumber
	 * @param code
	 * @return
	 */
	public String checkCode(String phonenumber,String code);
}
