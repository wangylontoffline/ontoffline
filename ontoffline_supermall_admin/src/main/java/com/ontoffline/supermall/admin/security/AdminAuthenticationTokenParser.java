/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.security;


import com.ontoffline.supermall.common.util.Json;
import com.ontoffline.supermall.security.provider.AuthenticationTokenParser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * @author wangylontoffline
 */

@Component
public class AdminAuthenticationTokenParser implements AuthenticationTokenParser {
    @Override
    public AbstractAuthenticationToken parse(String authenticationTokenStr) {
        AdminAuthenticationToken authRequest = Json.parseObject(authenticationTokenStr, AdminAuthenticationToken.class);
        return authRequest;
    }
}
