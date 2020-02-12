package com.online.taxi.passenger.service;

import com.online.taxi.common.dto.order.ForecastRequest;

/**
 * 
 * @author yueyi2019
 *
 */
public interface OrderService {
	/**
	 * 根据起止经纬度计算预估价格
	 * @param forecastRequest
	 * @return
	 */
	public Double forecast(ForecastRequest forecastRequest) ;
}
