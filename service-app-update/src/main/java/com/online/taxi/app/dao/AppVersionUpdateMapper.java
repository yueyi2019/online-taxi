package com.online.taxi.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.online.taxi.common.entity.AppVersionUpdate;
@Mapper
public interface AppVersionUpdateMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersionUpdate record);

    int insertSelective(AppVersionUpdate record);

    AppVersionUpdate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersionUpdate record);

    int updateByPrimaryKey(AppVersionUpdate record);
    
    List<AppVersionUpdate> selectByBean(AppVersionUpdate record);
}