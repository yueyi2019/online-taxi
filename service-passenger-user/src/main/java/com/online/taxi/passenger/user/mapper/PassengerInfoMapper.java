package com.online.taxi.passenger.user.mapper;

import com.online.taxi.common.entity.PassengerInfo;
import org.apache.ibatis.annotations.Mapper;
/**
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 *
 * @author yueyi2019
 */
@Mapper
public interface PassengerInfoMapper {
    /**
     * 根据id删除乘客信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入乘客信息
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
     * 根据id查询乘客信息
     * @param id
     * @return
     */
    PassengerInfo selectByPrimaryKey(Integer id);

    /**
     * 根据手机号查询乘客信息
     * @param phoneNumber
     * @return
     */
    PassengerInfo selectByPhoneNumber(String phoneNumber);

    /**
     * 更新乘客信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PassengerInfo record);

    /**
     * 更新乘客信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PassengerInfo record);
}