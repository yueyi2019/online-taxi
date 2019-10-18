package com.online.taxi.push.controller;

import com.online.taxi.push.response.PreGrabResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/accept")
public class AcceptController {

    @RequestMapping(value = "/get-data",produces = "text/event-stream;charset=utf-8")
    public String getData(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法进来了"+Math.random());
        PreGrabResponse preGrabResponse = new PreGrabResponse();
        preGrabResponse.setOrderId(new Random().nextInt());
        preGrabResponse.setStartAddress("天安门");
        preGrabResponse.setEndAddress("朝阳门");

        return "data:"+ JSONObject.fromObject(preGrabResponse)+"\n\n";
    }
}
