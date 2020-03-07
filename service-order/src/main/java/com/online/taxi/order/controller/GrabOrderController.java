package com.online.taxi.order.controller;

import com.online.taxi.common.dto.BaseResponse;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/grab")
public class GrabOrderController {

    @Autowired
    // 无锁
    @Qualifier("grabNoLockService")
    // jvm锁
//    @Qualifier("grabJvmLockService")
    // mysql锁
//    @Qualifier("grabMysqlLockService")
    // 单个redis
//    @Qualifier("grabRedisLockService")
    //单个redisson
//    @Qualifier("grabRedisRedissonService")    
    // 红锁
//    @Qualifier("grabRedisRedissonRedLockLockService")
    private GrabService grabService;
    
    
    @GetMapping("/do/{orderId}")
    public String grab(@PathVariable("orderId") int orderId, int driverId){
        grabService.grabOrder(orderId,driverId);
        ResponseResult.success(new BaseResponse());
        return "";
    }
}
