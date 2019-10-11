package com.online.taxi.passenger.user.mapper;

import com.online.taxi.common.entity.PassengerInfo;
import org.apache.ibatis.annotations.Mapper;
/**
 * 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类
 * 需要注意的是：这个接口中不可以定义同名的方法，因为会生成相同的id
 * 也就是说这个接口是不支持重载的
 */
@Mapper
public interface PassengerInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PassengerInfo record);

    int insertSelective(PassengerInfo record);

    PassengerInfo selectByPrimaryKey(Integer id);

    PassengerInfo selectByPhoneNumber(String phoneNumber);

    int updateByPrimaryKeySelective(PassengerInfo record);

    int updateByPrimaryKey(PassengerInfo record);
}