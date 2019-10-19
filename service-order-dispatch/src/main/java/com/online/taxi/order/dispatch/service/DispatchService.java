package com.online.taxi.order.dispatch.service;

import com.online.taxi.common.dto.ResponseResult;

import java.util.List;

/**
 * @author yueyi2019
 */
public interface DispatchService {

    /**
     * 将指定订单拍给多个司机
     * @param orderId
     * @param driverIdList
     * @return
     */
    public ResponseResult dispatch(int orderId , List<Integer> driverIdList);
}
