package com.online.taxi.verificationcode.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-event")
public class PublishController implements ApplicationContextAware,ApplicationEventPublisherAware{
	
	private ApplicationEventPublisher eventPublisher;
	
	private ApplicationContext applicationContext;
	
	@PostMapping("/publish")
	public boolean publishEvent(@RequestBody String content) {
		String serviceId = applicationContext.getId();
		CustomRemoteApplicationEvent event = new CustomRemoteApplicationEvent(content,serviceId,"destination");
		eventPublisher.publishEvent(event);
		return true;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.eventPublisher = applicationEventPublisher;
	}
	
	
	
}
