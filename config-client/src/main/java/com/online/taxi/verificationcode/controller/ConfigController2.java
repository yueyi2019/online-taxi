package com.online.taxi.verificationcode.controller;

import com.netflix.discovery.converters.Auto;
import com.online.taxi.verificationcode.component.GitConfig;
import com.online.taxi.verificationcode.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**测试获取属性的类
 * @author yueyi2019
 */
@RestController
@RequestMapping("/config2")
public class ConfigController2 {

	@Autowired
	private GitConfig gitConfig;

	@Autowired
	private ConfigService configService;
	
	@Value("${env1}")
	private String env;
	
	@GetMapping("/env01")
	public String env0() {
		return "远程："+env;
	}

	@GetMapping("/env")
	public String env() {
		return gitConfig.getEnv();
	}
	
	@GetMapping("/env1")
	public String env1() {

		return configService.getEnv1();
	}

	@GetMapping("/env2")
	public String env2() {
		return configService.getEnv2();
	}
}
