package com.online.taxi.passenger.ribbonconfigscan;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

import com.online.taxi.passenger.ribbonconfig.RibbonConfiguration;

@Configuration
@RibbonClient(name = "service-sms",configuration = RibbonConfiguration.class)
public class TestConfiguration {

}