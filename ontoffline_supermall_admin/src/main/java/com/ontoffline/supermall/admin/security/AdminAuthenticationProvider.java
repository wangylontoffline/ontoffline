/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.security;


import cn.hutool.core.util.StrUtil;
import com.ontoffline.supermall.common.util.RedisUtil;
import com.ontoffline.supermall.security.constants.SecurityConstants;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.exception.BadCredentialsExceptionBase;
import com.ontoffline.supermall.security.exception.ImageCodeNotMatchExceptionBase;
import com.ontoffline.supermall.security.exception.UsernameNotFoundExceptionBase;
import com.ontoffline.supermall.security.exception.BaseOntofflineAuth2Exception;
import com.ontoffline.supermall.security.provider.AbstractUserDetailsAuthenticationProvider;
import com.ontoffline.supermall.security.service.OntofflineUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author wangylontoffline
 */

@Component
@AllArgsConstructor
public class AdminAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private OntofflineUserDetailsService OntofflineUserDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    protected UserDetails retrieveUser(String username, Authentication authentication) throws BaseOntofflineAuth2Exception {

        AdminAuthenticationToken adminAuthenticationToken = (AdminAuthenticationToken) authentication;

        String kaptchaKey = SecurityConstants.SPRING_SECURITY_RESTFUL_IMAGE_CODE + adminAuthenticationToken.getSessionUUID();

        String kaptcha = RedisUtil.get(kaptchaKey);

        RedisUtil.del(kaptchaKey);

        if(StrUtil.isBlank(adminAuthenticationToken.getImageCode()) || !adminAuthenticationToken.getImageCode().equalsIgnoreCase(kaptcha)){
            throw new ImageCodeNotMatchExceptionBase("验证码有误");
        }

        UserDetails user;
        try {
            user = OntofflineUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundExceptionBase var6) {
            throw new UsernameNotFoundExceptionBase("账号或密码不正确");
        }

        String encodedPassword = user.getPassword();
        String rawPassword = authentication.getCredentials().toString();

        // 密码不正确
        if (!passwordEncoder.matches(rawPassword,encodedPassword)){
            throw new BadCredentialsExceptionBase("账号或密码不正确");
        }

        if (!user.isEnabled()) {
            throw new UsernameNotFoundExceptionBase("账号已被锁定,请联系管理员");
        }
        return user;
    }


    @Override
    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        AdminAuthenticationToken result = new AdminAuthenticationToken(user, authentication.getCredentials());
        result.setDetails(authentication.getDetails());
        return result;
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return AdminAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected App getAppInfo() {
        return null;
    }

}
