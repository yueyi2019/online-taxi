package com.online.taxi.common.entity;

import java.util.Date;

import lombok.Data;
/**
 * @author yueyi2019
 */
@Data
public class Sms {
    private Integer id;

    private String phoneNumber;

    private String smsContent;

    private Date sendTime;

    private String operator;

    private Integer sendFlag;

    private Integer sendNumber;

    private Date createTime;

    private Date updateTime;

    
}