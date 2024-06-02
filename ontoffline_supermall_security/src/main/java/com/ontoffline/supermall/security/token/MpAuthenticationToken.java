/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.token;


import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wangylontoffline
 */

@NoArgsConstructor
public class MpAuthenticationToken extends MyAuthenticationToken {


    public MpAuthenticationToken(UserDetails principal, Object credentials) {
        super(principal, credentials, principal.getAuthorities());
    }
}
