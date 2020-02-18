package com.online.taxi.zuul.filter;


import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author yueyi2019
 */
@Component
public class RateFilter extends ZuulFilter {
	/**
	 * 每秒1个令牌，实际通过压测获得
	 */
	private static final RateLimiter RATE_LIMITER  = RateLimiter.create(1);
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
				
		//拿不到令牌马上返回。
		if(!RATE_LIMITER.tryAcquire()) {
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
		}
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}
	
	/**
	 * 限流要最早
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return -10;
	}

}
