package com.online.taxi.common.entity;

import lombok.Data;

@Data
public class OrderLock {
    private Integer orderId;
    private Integer driverId;
    
}