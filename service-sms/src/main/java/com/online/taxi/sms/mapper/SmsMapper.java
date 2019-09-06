package com.online.taxi.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.Sms;

@Mapper
public interface SmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sms record);

    int insertSelective(Sms record);

    Sms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKey(Sms record);
}