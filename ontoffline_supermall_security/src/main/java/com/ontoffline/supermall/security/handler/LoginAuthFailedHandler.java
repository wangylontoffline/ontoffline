/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.handler;


import cn.hutool.core.util.CharsetUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.ontoffline.supermall.security.exception.BaseOntofflineAuth2Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangylontoffline
 */

/**
 * 登陆失败处理
 */
@Component
@Slf4j
public class LoginAuthFailedHandler implements AuthenticationFailureHandler {

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception) throws IOException {

        if (!(exception instanceof BaseOntofflineAuth2Exception)) {
            return;
        }

        BaseOntofflineAuth2Exception auth2Exception = (BaseOntofflineAuth2Exception) exception;

        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(auth2Exception.getHttpErrorCode());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(auth2Exception.getMessage());
    }

}