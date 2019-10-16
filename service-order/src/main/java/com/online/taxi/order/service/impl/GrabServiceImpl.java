package com.online.taxi.order.service.impl;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author yueyi2019
 */
@Service
public class GrabServiceImpl implements GrabService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * redis 客户端
     */
    @Autowired
    @Qualifier("redissonClientCostum")
    private RedissonClient redissonClientCostum;

    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ORDER_KEY_PRE + orderId).intern();
        String uuid = UUID.randomUUID().toString();

        RLock rLock = redissonClientCostum.getLock(lockKey);
        boolean isLock = rLock.tryLock();
        if (!isLock){
            System.out.println(Thread.currentThread().getName()+"加锁失败");
            return ResponseResult.fail(-1,"抢单失败");
        }else {
            System.out.println(Thread.currentThread().getName()+"加锁成功");
        }
        try {

            //通过断点模拟业务执行时间。
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行抢单逻辑");
        }finally {

            rLock.unlock();
        }
        return null;
    }
}
