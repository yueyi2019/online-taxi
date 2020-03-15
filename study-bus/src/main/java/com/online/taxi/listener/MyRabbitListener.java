package com.online.taxi.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;


@Component
@RabbitListener(queues = "q1")
public class MyRabbitListener {
	/**
	 * 消息问题：
	 * 消息重复消费，（消息消费后，放到布隆过滤器，位集合中，之前消费成功过，跳过，没成功过；否则继续消费）
	 * 消息堆积，（生产 比 消费快 或 消费者挂了。办法：1 并发消费（修改yml，设置并发数），2 上机器，3 再将消息分发到其他队列中，去其他队列消费，用多个消费者消费）
	 * 消费安全（1 将消息队列设置成持久化 2 业务逻辑失败，让消息重新回到队列，或者在消费次数达到一定值后，记录到数据库，让人工处理。）
	 * 延迟消费
	 * @param msg
	 * @param channel
	 * @param message
	 */
	@RabbitHandler
	public void handle(String msg,Channel channel,Message message) {
		MessageProperties messageProperties = message.getMessageProperties();
		long deliveryTag = messageProperties.getDeliveryTag();
		System.out.println("监听消息"+msg);
		try {
			// 拒绝消息，让消息回到队列中去，
			channel.basicReject(deliveryTag,true);
			
			// 若失败次数达到一定值，放数据库，让人工去处理。
			
			
			//处理成功，签收消息
//			channel.basicAck(deliveryTag, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
