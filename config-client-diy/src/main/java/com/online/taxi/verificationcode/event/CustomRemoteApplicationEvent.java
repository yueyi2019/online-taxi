package com.online.taxi.verificationcode.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class CustomRemoteApplicationEvent extends RemoteApplicationEvent {
	
	public CustomRemoteApplicationEvent(String content , String originService, String destinationService) {
		
		super(content,originService,destinationService);
		
	}
}
