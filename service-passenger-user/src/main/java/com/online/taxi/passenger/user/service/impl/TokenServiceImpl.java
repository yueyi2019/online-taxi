package com.online.taxi.passenger.user.service.impl;

import com.online.taxi.common.util.JwtUtil;
import com.online.taxi.passenger.user.service.TokenService;

import java.util.Date;

/**
 * @author yueyi2019
 */
public class TokenServiceImpl implements TokenService {

    /**
     * 生成token
     * @param subject
     * @return
     */
    @Override
    public String createToken(String subject) {
        String jwtStr = JwtUtil.createToken(subject,new Date());
        //存缓存，设置过期时间
        return null;
    }
}
