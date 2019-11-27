package com.online.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service-instance")
public class ServiceInstanceController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/query-by-application-name/{applicationName}")
	public List<ServiceInstance> getInstance(@PathVariable String applicationName){
		
		return discoveryClient.getInstances(applicationName);
		
	}

}
