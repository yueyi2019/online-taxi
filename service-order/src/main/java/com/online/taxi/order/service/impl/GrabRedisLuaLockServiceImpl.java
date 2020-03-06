package com.online.taxi.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.entity.OrderLock;
import com.online.taxi.order.lock.MysqlLock;
import com.online.taxi.order.lock.RedisLock;
import com.online.taxi.order.service.GrabService;

/**
 * @author yueyi2019
 */
@Service("grabRedisLuaLockService")
public class GrabRedisLuaLockServiceImpl implements GrabService {

	@Autowired
	private RedisLock lock;
	
    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        OrderLock ol = new OrderLock();
        ol.setOrderId(orderId);
        ol.setDriverId(driverId);
        
        lock.setOrderLock(ol);
        lock.lock();
        System.out.println("司机"+driverId+"加锁成功");


        try {
            //通过断点模拟业务执行时间。
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("司机:"+driverId+" 执行抢单逻辑");
        } finally {
        	System.out.println("司机"+driverId+"解锁成功");
            lock.unlock();
            
        }
        return null;
    }
}
