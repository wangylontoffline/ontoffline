/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

/**
 * @author wangylontoffline
 */

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean<>();
        //添加过滤器
        registration.setFilter((Filter) new XssFilter());
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        //设置优先级
        registration.setOrder(Integer.MAX_VALUE);
        registration.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
        return registration;
    }
    

}
