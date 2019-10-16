package com.online.taxi.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yueyi2019
 */
@Component
public class RedisSentinelConfig {

    @Autowired
    RedisSentinelProperties properties;

    @Bean(name = "redissonClientCostum")
    @Order(1)
    public RedissonClient getRedisson(){

        Config config = new Config();
        config.useSentinelServers()
                .setMasterName(properties.getMasterName())
                .addSentinelAddress(properties.getAddress())
                .setDatabase(0);
        return Redisson.create(config);
    }
}
