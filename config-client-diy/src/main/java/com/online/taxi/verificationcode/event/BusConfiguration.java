package com.online.taxi.verificationcode.event;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RemoteApplicationEventScan
public class BusConfiguration {

	@EventListener
	public void onUserRemoteApplicationEvent(CustomRemoteApplicationEvent event) {
		System.out.println("原始服务："+event.getOriginService()+",内容："+event.getSource());
	}
}
