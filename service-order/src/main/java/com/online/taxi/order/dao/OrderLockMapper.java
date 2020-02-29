package com.online.taxi.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.OrderLock;

@Mapper
public interface OrderLockMapper {
	
    int deleteByPrimaryKey(Integer orderId);

    int insertSelective(OrderLock record);
}