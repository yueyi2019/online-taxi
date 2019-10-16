package com.online.taxi.order.service.impl;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yueyi2019
 */
@Service
public class GrabServiceImpl implements GrabService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    @Qualifier("redisson")
    private Redisson redisson;

    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ORDER_KEY_PRE + orderId).intern();
        //redisson锁
        RLock rLock = redisson.getLock(lockKey);
        rLock.lock();


        //实现红锁加入下面这2行
//        RedissonRedLock redLock = new RedissonRedLock(rLock);
//        redLock.lock();


        try {
            //通过断点模拟业务执行时间。
            try {
                Thread.sleep(20000);
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
