package com.online.taxi.jms.provider.service.impl;

import com.online.taxi.jms.provider.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author yueyi2019
 */
@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void send(Destination destination, String message) {
        jmsTemplate.convertAndSend(destination,message);
    }
}
