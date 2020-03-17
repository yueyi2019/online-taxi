package com.online.taxi.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 *  pre filter
 * @author yueyi2019
 */
@Component
public class PreFilter extends ZuulFilter {

	/**
	 * 过滤器是否生效
	 */
	@Override
	public boolean shouldFilter() {
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		String uri = request.getRequestURI();
		System.out.println("pre来源uri："+uri);
		//只有此接口/api-passenger/api-passenger-gateway-test/hello才被拦截
//		String checkUri = "/api-passenger/api-passenger-gateway-test/hello";
//		if(checkUri.equalsIgnoreCase(uri)) {
//			return true;
//		}
//		
		return false;
		
//		return (boolean)requestContext.get("f");
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("pre拦截");
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		String token = request.getHeader("token");
		System.out.println("pre 业务逻辑 token:"+token);
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 4;
	}

}
