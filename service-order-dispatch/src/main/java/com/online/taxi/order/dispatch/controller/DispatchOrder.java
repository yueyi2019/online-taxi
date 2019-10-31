package com.online.taxi.order.dispatch.controller;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.order.dispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/dispatch")
public class DispatchOrder {

    @Autowired
    private DispatchService dispatchService;


    @GetMapping("/call/{orderId}")
    public ResponseResult callCar(@PathVariable("orderId") int orderId){
        //测试派给1号司机,选司机的逻辑
        List<Integer> driverList = new ArrayList<Integer>();
        driverList.add(1);
        return dispatchService.dispatch(orderId,driverList);
    }
}
