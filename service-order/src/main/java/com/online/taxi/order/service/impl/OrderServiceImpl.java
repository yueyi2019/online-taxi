package com.online.taxi.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.common.entity.Order;
import com.online.taxi.order.dao.OrderMapper;
import com.online.taxi.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper mapper;
	
	public boolean grab(int orderId, int driverId) {
		Order order = mapper.selectByPrimaryKey(orderId);
		 try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
		if(order.getStatus().intValue() == 0) {
			order.setStatus(1);
			mapper.updateByPrimaryKeySelective(order);
			
			return true;
		}
		return false;
		
	}
}
