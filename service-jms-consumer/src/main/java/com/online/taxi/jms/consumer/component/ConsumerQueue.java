package com.online.taxi.jms.consumer.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
 
/**
 * 消息消費者(Queue模式)
 */
@Component
public class ConsumerQueue {
    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     */
   @JmsListener(destination = "ActiveMQQueue",containerFactory = "jmsListenerContainerQueue")
   public void receiveQueue(String text) {
                  System.out.println("消息消費者收到的报文为:"+text);
   }
}
