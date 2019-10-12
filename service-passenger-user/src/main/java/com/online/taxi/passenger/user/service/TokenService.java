package com.online.taxi.passenger.user.service;

/**
 * @author yueyi2019
 */
public interface TokenService {
    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken(String subject);
}
