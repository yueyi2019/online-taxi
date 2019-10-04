package com.online.taxi.verificationcode.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

	@Value("${env}")
	private String env;
	
	@GetMapping("/env")
	public String env() {
		
		return "env:"+env;
	}
}
