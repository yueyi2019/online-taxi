package com.online.taxi.jms.consumer.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消息消費者(Queue模式)
 */
@Component
public class ConsumerQueue {
    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     */
   @JmsListener(destination = "1",containerFactory = "jmsListenerContainerQueue")
   public void receiveQueue1(TextMessage text, Session session) throws JMSException {
       try {
           int i = 1/0;
           System.out.println("消費者收到的queue1报文为:"+text.getText());
            text.acknowledge();
       }catch (Exception e){
            session.recover();
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e1) {
               e1.printStackTrace();
           }
           System.out.println("异常了");
       }

   }

//    @JmsListener(destination = "ActiveMQQueue")
//    public void receiveQueue2(String text) {
//        System.out.println("消費者收到的queue2报文为:"+text);
//    }
}
