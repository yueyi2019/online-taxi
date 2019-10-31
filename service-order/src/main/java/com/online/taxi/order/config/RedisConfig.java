package com.online.taxi.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yueyi2019
 */
@Component
public class RedisConfig {

    @Autowired
    RedisSentinelProperties properties;
    //以下为redisson锁，哨兵
//    @Bean(name = "redisson")
//    @Order(1)
//    public Redisson getRedisson(){
//
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName(properties.getMasterName())
//                .addSentinelAddress(properties.getAddress())
//                .setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }
    //以上为redisson锁

    //以下为红锁
    @Bean
    public RedissonClient redissonRed1(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setDatabase(0);
        return Redisson.create(config);
    }
//    @Bean
//    public RedissonClient redissonRed2(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("127.0.0.1:6380").setDatabase(0);
//        return Redisson.create(config);
//    }
//    @Bean
//    public RedissonClient redissonRed3(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("127.0.0.1:6381").setDatabase(0);
//        return Redisson.create(config);
//    }
    //以上为红锁
}
