package com.online.taxi.eureka.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * @author yueyi2019
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf
//		http.csrf().disable(); 
		/*
		 * 默认情况下添加SpringSecurity依赖的应用每个请求都需要添加CSRF token才能访问，Eureka客户端注册时并不会添加，所以需要配置/eureka/**路径不需要CSRF token。
		 */
		http.csrf().ignoringAntMatchers("/eureka/**");
		// 开启认证支持HttpBasic
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); 
	}
}