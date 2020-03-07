package com.online.taxi.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import com.online.taxi.order.service.OrderService;

@Service("grabJvmLockService")
public class GrabJvmLockServiceImpl implements GrabService {
	
	@Autowired
	OrderService orderService;
	
	@Override
	public ResponseResult grabOrder(int orderId, int driverId) {
		String lock = (orderId+"");
		
		synchronized (lock.intern()) {
			try {
				System.out.println("司机:"+driverId+" 执行抢单逻辑");
				
	            boolean b = orderService.grab(orderId, driverId);
	            if(b) {
	            	System.out.println("司机:"+driverId+" 抢单成功");
	            }else {
	            	System.out.println("司机:"+driverId+" 抢单失败");
	            }
	            
	        } finally {
	        	
	            
	        }
		}
		
		
		return null;
	}

}
