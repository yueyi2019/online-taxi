package com.online.taxi.order.lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import com.online.taxi.common.entity.OrderLock;
import com.online.taxi.order.dao.OrderLockMapper;

import lombok.Data;

@Service
@Data
public class RedisLock implements Lock {

	@Resource
	private RedisTemplate<Integer, Integer> redisTemplate;
	
	private OrderLock orderLock;
	
	@Override
	public void lock() {
		// 1、尝试加锁
		if(tryLock()) {
			return;
		}
		// 2.休眠
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 3.递归再次调用
		lock();
	}
	
	/**
	 * 	非阻塞式加锁，成功，就成功，失败就失败。直接返回
	 */
	@Override
	public boolean tryLock() {
		
		int orderId = orderLock.getOrderId();
		int driverId = orderLock.getDriverId();
		
		Boolean b = redisTemplate.opsForValue().setIfAbsent(orderId, driverId, 50, TimeUnit.SECONDS);
		if(b) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public void unlock() {
		
		DefaultRedisScript<List> getRedisScript = new DefaultRedisScript<List>();
        getRedisScript.setResultType(List.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/lock.lua")));
        
		redisTemplate.execute(getRedisScript, Arrays.asList(orderLock.getOrderId()), Arrays.asList(orderLock.getDriverId()));
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}


	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
