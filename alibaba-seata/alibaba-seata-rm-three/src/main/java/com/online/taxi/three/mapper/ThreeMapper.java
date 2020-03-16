package com.online.taxi.three.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.Sms;
import com.online.taxi.three.entity.Three;

/**
 * @author yueyi2019
 */
@Mapper
public interface ThreeMapper {
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
    int insertSelective(Three record);

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
    int updateByPrimaryKeySelective(Three record);

   
}