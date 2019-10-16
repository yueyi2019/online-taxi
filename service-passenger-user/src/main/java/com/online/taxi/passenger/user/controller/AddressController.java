package com.online.taxi.passenger.user.controller;

import com.online.taxi.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    /**
     * 根据乘客id和地址类型获取地址信息
     * @param passengerId
     * @param type
     * @return
     */
    @GetMapping("/get-address/{passengerId}/{type}")
    public ResponseResult getAddress(@PathVariable("passengerId") int passengerId,@PathVariable("type") int type){
        //获取乘客地址信息
        return null;
    }
}
