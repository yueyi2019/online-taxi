package com.online.taxi.order.service.impl;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yueyi2019
 */
public class StudyGrabServiceImpl{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 判断订单是否被抢，被抢走：true，没被抢：false
     * @param orderId
     * @return
     */
    private boolean checkOrder(int orderId){
        System.out.println("判断订单是否被抢");
        return true;
    }

    /**
     * 1
     * 讲解分布式锁用的代码，非正式
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder1(int orderId , int driverId){

        //获取订单，判断订单是否被抢
        boolean isGrab = checkOrder(orderId);
        if (isGrab){
            System.out.println("返回 订单已被抢");
        }else {
            //业务，绑定订单和司机，改变订单状态生成虚拟号码，向乘客推送 订单被抢的信息
            System.out.println("订单没被抢，当前司机执行抢单业务");
        }
        return null;

    }

    /**
     * 2
     * 图
     * 讲解分布式锁用的代码，非正式
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder2(int orderId , int driverId){
        synchronized (this){
            grabOrder1(orderId,driverId);
        }

        return null;

    }


    /**
     * 3
     * 讲解分布式锁用的代码，非正式
     * 不锁具体订单
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder3(int orderId , int driverId){
        String key = "lock";
        boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb");
        if (!flag){
            System.out.println("订单被人抢");
            return null;
        }
        grabOrder1(orderId,driverId);
        return null;
    }

    /**
     * 4
     * 讲解分布式锁用的代码，非正式
     * 没有删除key，key对不对？
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder4(int orderId , int driverId){
        String key = "lock_"+orderId;
        boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb");
        if (!flag){
            System.out.println("抢单司机太多");
            return null;
        }
        grabOrder1(orderId,driverId);
        return null;
    }

    /**
     * 5
     * 讲解分布式锁用的代码，非正式
     * 有异常，删不了key
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder5(int orderId , int driverId){
        String key = "lock_"+orderId;
        boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb");
        if (!flag){
            System.out.println("抢单司机太多");
            return null;
        }
        grabOrder1(orderId,driverId);
        redisTemplate.delete(key);
        return null;
    }

    /**
     * 6
     * 讲解分布式锁用的代码，非正式
     * 运维重启服务
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder6(int orderId , int driverId){
        String key = "lock_"+orderId;
        try {
            boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb");
            if (!flag){
                System.out.println("抢单司机太多");
                return null;
            }
            grabOrder1(orderId,driverId);
        }finally {
            redisTemplate.delete(key);
        }

        return null;
    }

    /**
     * 7
     * 讲解分布式锁用的代码，非正式
     * 加完锁没执行设置过期时间怎么办
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder7(int orderId , int driverId){
        String key = "lock_"+orderId;
        try {
            boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb");


            redisTemplate.expire(key,10, TimeUnit.SECONDS);
            if (!flag){
                System.out.println("抢单司机太多");
                return null;
            }
            grabOrder1(orderId,driverId);
        }finally {
            redisTemplate.delete(key);
        }

        return null;
    }

    /**
     *8
     * 讲解分布式锁用的代码，非正式
     * 前面的程序把后面的锁释放了，怎么办
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder8(int orderId , int driverId){
        String key = "lock_"+orderId;
        try {
            //原子操作
            boolean flag = redisTemplate.opsForValue().setIfAbsent(key,"msb",10,TimeUnit.SECONDS);


            if (!flag){
                System.out.println("抢单司机太多");
                return null;
            }
            grabOrder1(orderId,driverId);
        }finally {
            redisTemplate.delete(key);
        }

        return null;
    }

    /**
     *9
     * 讲解分布式锁用的代码，非正式
     * 万一锁自动超时释放了怎么办？
     * @param orderId
     * @param driverId
     * @return
     */
    public ResponseResult grabOrder9(int orderId , int driverId){
        String key = "lock_"+orderId;
        String uuid = UUID.randomUUID().toString();
        try {
            //原子操作
            boolean flag = redisTemplate.opsForValue().setIfAbsent(key,uuid,10,TimeUnit.SECONDS);

            if (!flag){
                System.out.println("抢单司机太多");
                return null;
            }
            grabOrder1(orderId,driverId);
        }finally {
            if (uuid.equals(redisTemplate.opsForValue().get(key))){
                redisTemplate.delete(key);
            }
        }

        return null;
    }


}
