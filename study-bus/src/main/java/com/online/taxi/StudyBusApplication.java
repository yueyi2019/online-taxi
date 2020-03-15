package com.online.taxi;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/rabbit")
public class StudyBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBusApplication.class, args);
	}

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/send")
	public String send(@RequestBody String content) {
		
		rabbitTemplate.convertAndSend("direct.exc", "r1", "test bus",new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				
				return message;
			}
		});
		return "发送成功";
	}
}
