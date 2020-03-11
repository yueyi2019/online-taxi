package com.online.taxi.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Component
public class MsbFallback implements FallbackProvider{

	/**
	 * 表明为哪个微服务提供回退
	 * 服务Id ，若需要所有服务调用都支持回退，返回null 或者 * 即可
	 */
	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
//		return "*";
		// 下面是单独的微服务
		return "api-passenger";
//		return "api-driver";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		
		if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		
	}
	
	private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //return status;
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //return status.value();
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //return status.getReasonPhrase();
                //return HttpStatus.BAD_REQUEST.name();
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
            	String msg = "{\"msg\":\"服务故障\"}";
            	return new ByteArrayInputStream(msg.getBytes());
//                return new ByteArrayInputStream("服务故障，请稍后再试fallback:".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

}
