package com.online.taxi.verificationcode.service;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.verificationcode.VerifyCodeResponse;
/**
 * @author yueyi2019
 */
public interface VerifyCodeService {

    /**
     * 根据身份和手机号生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult<VerifyCodeResponse> generate(int identity , String phoneNumber);

    /**
     * 校验身份，手机号，验证码的合法性
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verify(int identity,String phoneNumber,String code);
}
