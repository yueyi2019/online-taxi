package com.online.taxi.common.dao;

import com.online.taxi.common.entity.PassengerInfo;
/**
 * @author yueyi2019
 */
public interface PassengerInfoMapper {
    /**
     *  根据id删除乘客信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  插入乘客信息
     * @param record
     * @return
     */
    int insert(PassengerInfo record);

    /**
     * 插入乘客信息
     * @param record
     * @return
     */
    int insertSelective(PassengerInfo record);

    /**
     *  根据id查询乘客信息
     * @param id
     * @return
     */
    PassengerInfo selectByPrimaryKey(Integer id);

    /**
     *  更新乘客信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PassengerInfo record);

    /**
     *  更新乘客信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PassengerInfo record);
}