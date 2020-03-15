package com.online.taxi.verificationcode.controller;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.org.apache.regexp.internal.recompile;


@RestController
@RequestMapping("/git-webhook")
public class WebhookController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/bus-refresh")
	public String refresh() {
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost/actuator/bus-refresh",
                request, String.class);

		
		return "webhook刷新成功";
	}
}
