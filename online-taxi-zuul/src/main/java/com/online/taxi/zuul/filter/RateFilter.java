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
	
	private static int count = 0;
	/**
	 * 如果是1，表示每秒1个令牌，实际通过压测获得
	 * 
	 * 1、创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
	 * 2、当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
	 * 3、当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，
	 * 		会囤积最多permitsPerSecond个请求
	*/
	private static final RateLimiter RATE_LIMITER  = RateLimiter.create(5);
	
	@Override
	public boolean shouldFilter() {
		// 此处可以写判断地址
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
				
		requestContext.set("f", false);
		/**
		 * 拿不到令牌马上返回。尝试获取桶里的令牌，如果有，则返回true，
		 *并且，总的令牌数减1。没有则返回false。
		 */
//		if(!RATE_LIMITER.tryAcquire()) {
			System.out.println("rate filter 拿不到令牌，被限流了"+count++);
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
//		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	/**
	 * 限流要最早
	 */
	@Override
	public int filterOrder() {
		return -10;
	}

}
