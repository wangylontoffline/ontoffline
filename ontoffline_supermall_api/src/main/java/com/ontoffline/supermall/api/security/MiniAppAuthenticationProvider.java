/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.security;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.BooleanUtil;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.exception.UsernameNotFoundExceptionBase;
import com.ontoffline.supermall.security.exception.WxErrorExceptionBase;
import com.ontoffline.supermall.security.model.AppConnect;
import com.ontoffline.supermall.security.provider.AbstractUserDetailsAuthenticationProvider;
import com.ontoffline.supermall.security.service.YamiUser;
import com.ontoffline.supermall.security.service.YamiUserDetailsService;
import com.ontoffline.supermall.security.token.MyAuthenticationToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author wangylontoffline
 */

@Component
@AllArgsConstructor
public class MiniAppAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

//    private final YamiUserDetailsService yamiUserDetailsService;
	@Autowired
	private YamiUserDetailsService yamiUserDetailsService;

//    private final WxMaService wxMaService;
	@Autowired
	private WxMaService wxMaService;

    @Override
    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        MiniAppAuthenticationToken result = new MiniAppAuthenticationToken(user, authentication.getCredentials());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    protected UserDetails retrieveUser(String code, Authentication authentication) throws AuthenticationException {
        YamiUser loadedUser = null;
        // 如果使用debugger 模式，则返回debugger的用户
        if (BooleanUtil.isTrue(((MyAuthenticationToken)authentication).getDebugger())) {
            loadedUser = new YamiUser("1" , "debuggerOpenId1" ,  this.getAppInfo().value(), true);
            loadedUser.setDebugger(true);
            return loadedUser;
        }

        WxMaJscode2SessionResult session = null;

        AppConnect appConnect = new AppConnect();
        appConnect.setAppId(this.getAppInfo().value());
//        appConnect.setAppId("wx86a3910ee376619b");
        try {

            session = wxMaService.getUserService().getSessionInfo(code.trim());

            loadedUser = yamiUserDetailsService.loadUserByAppIdAndBizUserId(this.getAppInfo(),session.getOpenid());
        } catch (WxErrorException e) {
            throw new WxErrorExceptionBase(e.getMessage());
        } catch (UsernameNotFoundExceptionBase var6) {
            if (session == null) {
                throw new WxErrorExceptionBase("无法获取用户登陆信息");
            }
            appConnect.setBizUserId(session.getOpenid());
            appConnect.setBizUnionid(session.getUnionid());
            yamiUserDetailsService.insertUserIfNecessary(appConnect);
        }

        if (loadedUser == null) {
            loadedUser = yamiUserDetailsService.loadUserByAppIdAndBizUserId(this.getAppInfo(), appConnect.getBizUserId());
        }
        return loadedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MiniAppAuthenticationToken.class.isAssignableFrom(authentication);
    }


    @Override
    protected App getAppInfo() {
        return App.MINI;
    }
}
