package com.online.taxi.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.sms.service.AliService;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/send")
@SuppressWarnings("all")
@Slf4j
public class SendController {
	
	@Autowired
	private AliService aliService;
	
	
	@RequestMapping(value = "/alisms-template",method = RequestMethod.POST)
    public ResponseResult send(@RequestBody SmsSendRequest smsSendRequest){
		//输出收到的参数内容
        JSONObject param = JSONObject.fromObject(smsSendRequest);
        log.info(param.toString());
        aliService.sendSms(smsSendRequest);
        return  ResponseResult.success("");
    }
	
}
