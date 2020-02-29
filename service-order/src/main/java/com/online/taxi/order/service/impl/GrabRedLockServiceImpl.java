package com.online.taxi.order.service.impl;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yueyi2019
 */
@Service
public class GrabRedLockServiceImpl implements GrabService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @Autowired
//    @Qualifier("redisson")
//    private Redisson redisson;

    @Autowired
    private RedissonClient redissonRed1;
    @Autowired
    private RedissonClient redissonRed2;
    @Autowired
    private RedissonClient redissonRed3;

    @Override
    public ResponseResult grabOrder(int orderId , int driverId){
        //生成key
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ORDER_KEY_PRE + orderId).intern();
        //redisson锁 哨兵
//        RLock rLock = redisson.getLock(lockKey);
//        rLock.lock();

        //redisson锁 单节点
        RLock rLock = redissonRed1.getLock(lockKey);

        //红锁
//        RLock rLock1 = redissonRed1.getLock(lockKey);
//        RLock rLock2 = redissonRed2.getLock(lockKey);
//        RLock rLock3 = redissonRed2.getLock(lockKey);
//        RedissonRedLock rLock = new RedissonRedLock(rLock1,rLock2,rLock3);

        rLock.lock();

//        rLock.tryLock();

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
