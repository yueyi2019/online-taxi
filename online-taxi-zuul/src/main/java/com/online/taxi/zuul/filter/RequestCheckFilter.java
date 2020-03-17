package com.online.taxi.zuul.filter;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 * 	请求合法check
 * @author yueyi2019
 */
@Component
public class RequestCheckFilter extends ZuulFilter {
	
	private String secret = "7yuijhyt45de2";

	/**
	 * 	该过滤器是否生效
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	/**
	 * 	拦截后的具体业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("request check 拦截");
		//获取上下文（重要，贯穿 所有filter，包含所有参数）
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		Long timestamp = Long.valueOf(request.getHeader("timestamp"));
		String token = request.getHeader("token");
		String sign = request.getHeader("sign");
		
		String localSign = DigestUtils.sha1Hex(token + timestamp + secret);
		boolean flag = true;
		// 判断时间戳是不是在1分钟以内
		Long now = Calendar.getInstance().getTimeInMillis();
		if(!(flag && (now - timestamp < 1 * 1000))) {
			flag = false;
		}
		
		if(!(flag && (localSign.trim().equals(sign)))) {
			flag = false;
		}
		
		if(flag) {
			System.out.println("请求合法");
		}else {
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(444);
			requestContext.setResponseBody("非法请求");
			System.out.println("请求非法");
		}
		
		
		return null;
	}
	/**
	 * 拦截类型，4中类型。
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * 	值越小，越在前
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	

}
