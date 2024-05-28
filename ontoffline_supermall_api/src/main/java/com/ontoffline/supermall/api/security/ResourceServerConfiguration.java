/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ontoffline.supermall.security.filter.LoginAuthenticationFilter;

/**
 * @author wangylontoffline
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	private static final String RESOURCE_ID = "p";

    @Autowired
    private LoginAuthenticationFilter loginAuthenticationFilter;
    
//    @Autowired
//    private OTOMLoginFilter otomloginFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//    	http.addFilter(otomloginFilter);
        http
        .addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//         Since we want the protected resources to be accessible in the UI as well we need
//         session creation to be allowed (it's disabled by default in 2.0.6)
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
         .and()
         .requestMatchers().anyRequest()
         .and()
         .anonymous()
         .and()
         .authorizeRequests()
         //配置/p访问控制，必须认证过后才可以访问
         .antMatchers("/"+RESOURCE_ID+"/**").authenticated();
        
         
//        .and()
//        .authorizeRequests().anyRequest()
//        //配置/p访问控制，必须认证过后才可以访问
//        .antMatchers("/p/**").authenticated();
    }
    
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .requestMatchers()
//                // 保险起见，防止被主过滤器链路拦截
//                .antMatchers("/account/**")
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/account/info/**").access("#oauth2.hasScope('get_user_info')")
//                .antMatchers("/account/child/**").access("#oauth2.hasScope('get_childlist')");
//    }


}
