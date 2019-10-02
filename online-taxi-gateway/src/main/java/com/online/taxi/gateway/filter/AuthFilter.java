package com.online.taxi.gateway.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AuthFilter extends ZuulFilter {
	
	/**
	 * 过滤器类型：前置
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		//标明这是一个前置过滤器
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
