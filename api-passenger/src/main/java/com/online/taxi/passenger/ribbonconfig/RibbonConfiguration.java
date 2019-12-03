package com.online.taxi.passenger.ribbonconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
/**
 * 该类不应该在主应用程序的扫描之下，需要修改启动类的扫描配置。
 * 
 * @author yueyi2019
 *
 */
public class RibbonConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}
