package com.online.taxi.jms.provider.controller;

import com.online.taxi.jms.provider.service.ProduceService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/produce")
public class ProduceController {

    @Autowired
    private ProduceService produceService;


    @PostMapping("/order")
    public String send(String destination , String message){
        produceService.send(new ActiveMQQueue(destination),message);
        return "发送成功";
    }
}
