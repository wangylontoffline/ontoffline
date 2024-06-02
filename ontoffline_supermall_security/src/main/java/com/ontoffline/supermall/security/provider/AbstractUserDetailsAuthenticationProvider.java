/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.provider;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ontoffline.supermall.security.enums.App;

/**
 * @author wangylontoffline
 */

@Slf4j
public abstract class AbstractUserDetailsAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);


    @Override
    public final void afterPropertiesSet() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() == null?"NONE_PROVIDED":authentication.getName();
        UserDetails user;

        try {
            user = this.retrieveUser(username, authentication);
        } catch (UsernameNotFoundException var6) {
            logger.debug("User \'" + username + "\' not found");

            throw var6;
        }

        return this.createSuccessAuthentication(authentication, user);
    }

    protected abstract Authentication createSuccessAuthentication(Authentication authentication, UserDetails user);

    protected abstract App getAppInfo();

    protected abstract UserDetails retrieveUser(String username, Authentication authentication) throws AuthenticationException;



}
