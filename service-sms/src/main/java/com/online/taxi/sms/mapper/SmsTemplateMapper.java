package com.online.taxi.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.SmsTemplate;

@Mapper
public interface SmsTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsTemplate record);

    int insertSelective(SmsTemplate record);

    SmsTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsTemplate record);

    int updateByPrimaryKey(SmsTemplate record);
    
    SmsTemplate selectByTemplateId(String templateId);
}