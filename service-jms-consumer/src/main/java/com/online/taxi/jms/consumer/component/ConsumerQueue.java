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
   @JmsListener(destination = "ActiveMQQueue")
   public void receiveQueue1(String text) {
                  System.out.println("消費者收到的queue1报文为:"+text);
   }

    @JmsListener(destination = "ActiveMQQueue")
    public void receiveQueue2(String text) {
        System.out.println("消費者收到的queue2报文为:"+text);
    }
}
