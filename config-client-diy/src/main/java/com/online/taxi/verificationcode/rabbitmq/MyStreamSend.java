package com.online.taxi.verificationcode.rabbitmq;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
@RequestMapping("/rabbitmq")
public class MyStreamSend {

    @Resource
    private MessageChannel output;

    @PostMapping("/send")
    public String sendTestData(@RequestBody String content) {
        this.output.send(MessageBuilder.withPayload(content).build());  // 发出消息
        return "发送成功";
    }
}