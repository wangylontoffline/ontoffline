/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.provider;


import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author wangylontoffline
 */

public interface AuthenticationTokenParser {
    AbstractAuthenticationToken parse(String authenticationTokenStr);
}
