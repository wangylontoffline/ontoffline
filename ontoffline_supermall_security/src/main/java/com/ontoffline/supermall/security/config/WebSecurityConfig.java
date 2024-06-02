/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.config;


import com.ontoffline.supermall.security.filter.LoginAuthenticationFilter;
import com.ontoffline.supermall.security.filter.OntofflineLoginFilter;
import com.ontoffline.supermall.security.handler.LoginAuthFailedHandler;
import com.ontoffline.supermall.security.handler.LoginAuthSuccessHandler;
import com.ontoffline.supermall.security.provider.AuthenticationTokenParser;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author wangylontoffline
 */

@Configuration
@Order(90)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;

    @Autowired
    private LoginAuthFailedHandler loginAuthFailedHandler;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationTokenParser authenticationTokenParser;
    

    @Override
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
////        .addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
////        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////        .and()
//        .requestMatchers().anyRequest()
//        .and()
//        .authorizeRequests()
//        .antMatchers("/oauth/**").permitAll();
//        // @formatter:on
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        try {
            filter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthFailedHandler);
        filter.setAuthenticationTokenParser(authenticationTokenParser);
        return filter;
    }
    
    @Bean
    public OntofflineLoginFilter otomloginFilter() {
    	OntofflineLoginFilter filter = new OntofflineLoginFilter();
        filter.setFilterProcessesUrl("/otomlogin");
        try {
            filter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthFailedHandler);
        filter.setAuthenticationTokenParser(authenticationTokenParser);
        return filter;
    }

}
