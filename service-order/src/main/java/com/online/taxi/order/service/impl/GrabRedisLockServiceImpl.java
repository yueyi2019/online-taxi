package com.online.taxi.order.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.entity.OrderLock;
import com.online.taxi.order.lock.MysqlLock;
import com.online.taxi.order.lock.RedisLock;
import com.online.taxi.order.service.GrabService;
import com.online.taxi.order.service.OrderService;

/**
 * @author yueyi2019
 */
@Service("grabRedisLockService")
public class GrabRedisLockServiceImpl implements GrabService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	OrderService orderService;
	
    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
    	String lock = "order_"+(orderId+"");
    	/*
    	 *  情况一，如果锁没执行到释放，比如业务逻辑执行一半，运维重启服务，或 服务器挂了，没走 finally，怎么办？
    	 *  加超时时间
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"");
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 *  情况二：加超时时间,会有加不上的情况，运维重启
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"");
//    	stringRedisTemplate.expire(lock.intern(), 30L, TimeUnit.SECONDS);
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 * 情况三：超时时间应该一次加，不应该分2行代码，
    	 * 
    	 */
    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"", 30L, TimeUnit.SECONDS);
    	if(!lockStatus) {
    		return null;
    	}
    	
    	try {
			System.out.println("司机:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
            	System.out.println("司机:"+driverId+" 抢单成功");
            }else {
            	System.out.println("司机:"+driverId+" 抢单失败");
            }
            
        } finally {
        	/**
        	 * 这种释放锁有，可能释放了别人的锁。
        	 */
//        	stringRedisTemplate.delete(lock.intern());
        	
        	/**
        	 * 下面代码避免释放别人的锁
        	 */
        	if((driverId+"").equals(stringRedisTemplate.opsForValue().get(lock.intern()))) {
        		stringRedisTemplate.delete(lock.intern());
        	}
        }
        return null;
    }
}
