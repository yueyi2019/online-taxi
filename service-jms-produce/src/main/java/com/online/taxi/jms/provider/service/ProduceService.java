package com.online.taxi.jms.provider.service;

import javax.jms.Destination;

/**
 * @author yueyi2019
 */
public interface ProduceService {

    /**
     * 向指定队列发送消息
     * @param destination
     * @param message
     */
    public void send(Destination destination , String message);
}
