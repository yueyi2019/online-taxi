package com.online.taxi.app.service;

import java.util.List;

import com.online.taxi.common.entity.AppVersionUpdate;

public interface AppVersionUpdateService {

	List<AppVersionUpdate> selectByBean(Integer platformType,Integer appType,Integer versionCode);
	
}
