package com.online.taxi.order.controller;

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
//    @Qualifier("grabNoLockService")
    // jvm锁
//    @Qualifier("grabJvmLockService")
    // mysql锁
    @Qualifier("grabMysqlLockService")
//    @Qualifier("grabRedisLuaLockService")
    private GrabService grabService;
    
    @GetMapping("/do/{orderId}")
    public String grab(@PathVariable("orderId") int orderId, int driverId){
        grabService.grabOrder(orderId,driverId);
        return "抢单成功";
    }
}
