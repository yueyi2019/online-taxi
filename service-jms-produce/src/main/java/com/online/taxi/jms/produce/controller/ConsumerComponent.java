package com.online.taxi.jms.produce.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author yueyi2019
 */
@Component
public class ConsumerComponent {

    @JmsListener(destination = "d1")
    public void receiveQueue(String message){
        System.out.println("消费消息："+message);
    }
}