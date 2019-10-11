package com.online.taxi.passenger.user.dao;

import com.online.taxi.common.entity.PassengerInfo;
import com.online.taxi.passenger.user.mapper.PassengerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerInfoDao {

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    public PassengerInfo selectByPhoneNumber(String phoneNumber){
        return passengerInfoMapper.selectByPhoneNumber(phoneNumber);
    }
}
