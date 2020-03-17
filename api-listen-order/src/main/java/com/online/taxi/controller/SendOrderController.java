package com.online.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.listen.service.ListenService;

@RestController
@RequestMapping("/order")
public class SendOrderController {
	
	@Autowired
    private ListenService listenService;
	
	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
	@GetMapping("/send")
	public String sendOrder(String driverId) {
		
		String key = RedisKeyConstant.DRIVER_LISTEN_ORDER_PRE + driverId;
        redisTemplate.opsForValue().set(key, driverId);
		
        return "给"+driverId+"司机 发订单成功";
	}
}
