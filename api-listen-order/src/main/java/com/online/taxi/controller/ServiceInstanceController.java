package com.online.taxi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service-instance")
public class ServiceInstanceController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/query-by-application-name/{applicationName}")
	public List<ServiceInstance> getInstance(@PathVariable String applicationName){
		List<ServiceInstance> instances =  discoveryClient.getInstances(applicationName);
		for (ServiceInstance serviceInstance : instances) {
			Map<String, String> metadata = serviceInstance.getMetadata();
			String metaValue = metadata.get("yueyi");
			log.info("元数据："+metaValue);
		}
		return instances;
		
	}

}
