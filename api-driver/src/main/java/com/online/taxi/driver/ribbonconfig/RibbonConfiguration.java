package com.online.taxi.driver.ribbonconfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.online.taxi.driver.annotation.ExcudeRibbonConfig;
/**
 * 该类不应该在主应用程序的扫描之下，需要修改启动类的扫描配置。
 * 
 * @author yueyi2019
 *
 */
@Configuration
//@ExcudeRibbonConfig
public class RibbonConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * 修改IRule
	 * @return
	 */
//	@Bean
//	public IRule ribbonRule() {
//		return new RandomRule();
//	}
	
	/**
	 * 自定义rule
	 * @return
	 */
	@Bean
	public IRule ribbonRule() {
		return new MsbRandomRule();
	}
	
}
