package com.online.taxi.order.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yueyi2019
 */
@Component
public class RedisConfig {

    @Autowired
    RedisSentinelProperties properties;

    @Bean(name = "redisson")
    @Order(1)
    public Redisson getRedisson(){

        Config config = new Config();
        config.useSentinelServers()
                .setMasterName(properties.getMasterName())
                .addSentinelAddress(properties.getAddress())
                .setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
