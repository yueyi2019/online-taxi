package com.online.taxi.listen.service.impl;

import com.online.taxi.common.constant.RedisKeyConstant;
import com.online.taxi.listen.response.PreGrabResponse;
import com.online.taxi.listen.service.ListenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author yueyi2019
 */
@Service
public class ListenServiceImpl implements ListenService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public PreGrabResponse listen(int driverId) {
        String key = RedisKeyConstant.DRIVER_LISTEN_ORDER_PRE + driverId;
        String orderId = redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        PreGrabResponse preGrabResponse = new PreGrabResponse();
        try {
            if (StringUtils.isNotBlank(orderId)){
                preGrabResponse.setEndAddress("终点"+new Random().nextInt());
                preGrabResponse.setStartAddress("起点"+new Random().nextInt());
                preGrabResponse.setOrderId(Integer.parseInt(orderId));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return preGrabResponse;
        }


    }
}
