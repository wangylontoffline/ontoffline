/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.handler;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangylontoffline
 */

/**
 * 登录成功，返回oauth token
 *
 */
@Component
@Slf4j
public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LoginAuthSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    @Lazy
    private AuthorizationServerTokenServices yamiTokenServices;

    /**
     * Called when a user has been successfully authenticated.
     * 调用spring security oauth API 生成 oAuth2AccessToken
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        try {

            // ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);


            String grantType = request.getParameter(OAuth2Utils.GRANT_TYPE);
            // 目前先简化登陆传入的参数
            String clientId = grantType;
            // 简化
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId(clientId);

            TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, Sets.newHashSet(), grantType);


            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);


//            OAuth2AccessToken accessToken = defaultAuthorizationServerTokenServices.getAccessToken(oAuth2Authentication);
//            OAuth2AccessToken oAuth2AccessToken = null;
//            if (accessToken != null) {
//                oAuth2AccessToken = defaultAuthorizationServerTokenServices.refreshAccessToken(accessToken.getRefreshToken().getValue(), tokenRequest);
//            } else {
//                oAuth2AccessToken = defaultAuthorizationServerTokenServices.createAccessToken(oAuth2Authentication);
//            }

            OAuth2AccessToken oAuth2AccessToken = yamiTokenServices.createAccessToken(oAuth2Authentication);
            logger.info("获取token 成功：{}", oAuth2AccessToken.getValue());

            response.setCharacterEncoding(CharsetUtil.UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter printWriter = response.getWriter();
            printWriter.append(objectMapper.writeValueAsString(oAuth2AccessToken));
        } catch (IOException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

    }


}