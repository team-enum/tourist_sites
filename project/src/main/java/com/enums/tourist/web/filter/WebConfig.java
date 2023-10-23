package com.enums.tourist.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class WebConfig {
	
	@Bean
	public FilterRegistrationBean logFilter() {
		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");//모든 url에 대해 적용
		return filterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean loginCheckFilter() {
		
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*");//모든 url에 대해 적용
		return filterRegistrationBean;
	}
	
	
}





























