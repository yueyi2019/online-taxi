package com.online.taxi.verificationcode.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class MyStreamListener {

    @StreamListener(Sink.INPUT)
    public void input(String s){

        System.out.println("监听 消息队列 手动的内容 : " + s);
    }
}