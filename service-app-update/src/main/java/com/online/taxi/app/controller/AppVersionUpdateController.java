package com.online.taxi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.app.service.AppVersionUpdateService;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.common.dto.appUpdate.AppUpdateVersionRequest;
import com.online.taxi.common.entity.AppVersionUpdate;

@RestController
@RequestMapping("/app-version")
public class AppVersionUpdateController {
	
	@Autowired
	private AppVersionUpdateService appVersionUpdateService;
	
	@PostMapping("/latest")
	public ResponseResult queryLatestAppVersion(@RequestBody AppUpdateVersionRequest appUpdateVersionRequest) {
		Integer platformType = appUpdateVersionRequest.getPlatformType();
		Integer appType = appUpdateVersionRequest.getAppType();
		Integer versionCode = appUpdateVersionRequest.getVersionCode();
		List<AppVersionUpdate> list = appVersionUpdateService.selectByBean(platformType, appType, versionCode);
		
		return ResponseResult.success(list);
	}
}
