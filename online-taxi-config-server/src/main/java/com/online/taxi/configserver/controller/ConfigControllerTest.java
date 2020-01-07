package com.online.taxi.configserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigControllerTest {
	
	private String env;
	
	@GetMapping("/env")
	public String test() {
		return env;
	}
}
