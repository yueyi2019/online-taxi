package com.online.taxi.passenger.user.controller;

import com.netflix.discovery.converters.Auto;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.passengeruser.LoginRequest;
import com.online.taxi.common.entity.PassengerInfo;
import com.online.taxi.passenger.user.dao.PassengerInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PassengerInfoDao dao;

    @PostMapping("/passenger")
    public ResponseResult passengerLogin(@RequestBody LoginRequest request){
        String phoneNumber = request.getPhoneNumber();
        PassengerInfo passengerInfo = dao.selectByPhoneNumber(phoneNumber);
        if (passengerInfo == null){
            //插入用户

        }else {
            //更新最近登录时间
        }
        //生成通过jwt 生成 token，以后需要登录认证的接口，都需要带上token，还有签名规则

        return ResponseResult.success(null);
    }
}
