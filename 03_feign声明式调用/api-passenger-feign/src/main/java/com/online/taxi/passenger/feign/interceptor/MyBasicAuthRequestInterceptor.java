package com.online.taxi.passenger.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyBasicAuthRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		// TODO Auto-generated method stub
		// 有工具类，可以将root，root转换成下面字符串，u，p
		template.header("Authorization", "Basic cm9vdDpyb290");
	}
}
