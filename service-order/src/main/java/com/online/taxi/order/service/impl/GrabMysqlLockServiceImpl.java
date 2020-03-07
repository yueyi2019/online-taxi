package com.online.taxi.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.entity.OrderLock;
import com.online.taxi.order.lock.MysqlLock;
import com.online.taxi.order.service.GrabService;
import com.online.taxi.order.service.OrderService;

/**
 * @author yueyi2019
 */
@Service("grabMysqlLockService")
public class GrabMysqlLockServiceImpl implements GrabService {

	@Autowired
	private MysqlLock lock;
	
	@Autowired
	OrderService orderService;
	
	ThreadLocal<OrderLock> orderLock = new ThreadLocal<>();
	
    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        OrderLock ol = new OrderLock();
        ol.setOrderId(orderId);
        ol.setDriverId(driverId);
        
        orderLock.set(ol);
        lock.setOrderLockThreadLocal(orderLock);
        lock.lock();
//        System.out.println("司机"+driverId+"加锁成功");

        try {
			System.out.println("司机:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
            	System.out.println("司机:"+driverId+" 抢单成功");
            }else {
            	System.out.println("司机:"+driverId+" 抢单失败");
            }
            
        } finally {
        	
            lock.unlock();
        }
        
        return null;
    }
}
