package com.online.taxi.one.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.Sms;
import com.online.taxi.one.entity.One;

/**
 * @author yueyi2019
 */
@Mapper
public interface OneMapper {
    /**
     * 根据id删除短信记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入短信记录
     * @param record
     * @return
     */
    int insertSelective(One record);

    /**
     * 根据id查询短信记录
     * @param id
     * @return
     */
    Sms selectByPrimaryKey(Integer id);

    /**
     * 更新短信记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(One record);

   
}