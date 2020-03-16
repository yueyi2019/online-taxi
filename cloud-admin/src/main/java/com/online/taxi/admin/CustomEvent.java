package com.online.taxi.admin;

import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEvent {
	
	@EventListener
	public void listen(HeartbeatEvent e) {
		System.out.println(e.getSource()+"  admin 监控");
		//发送邮件，短信，电话。
	}
}