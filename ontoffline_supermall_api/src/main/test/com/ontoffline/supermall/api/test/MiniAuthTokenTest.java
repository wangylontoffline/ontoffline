/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.test;


import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.ontoffline.supermall.api.security.MiniAppAuthenticationToken;
import com.ontoffline.supermall.common.util.Json;

/**
 * @author wangylontoffline
 */


public class MiniAuthTokenTest {

	public MiniAuthTokenTest() {
		// TODO Auto-generated constructor stub
	}
	
    public static AbstractAuthenticationToken parse(String authenticationTokenStr) {
        MiniAppAuthenticationToken  authRequest = Json.parseObject(authenticationTokenStr, MiniAppAuthenticationToken.class);
        return authRequest;
    }
    
    public static AbstractAuthenticationToken parse(String authenticationTokenStr, MiniAppAuthenticationToken token) {
        MiniAppAuthenticationToken  authRequest = Json.parseObject(authenticationTokenStr, token.getClass());
        return authRequest;
    }
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String reqstr = "{\"grant_type\": \"mini_app\", \"appid\": \"wx86a3910ee376619b\",\"code\": \"0e1EgsFa1TXnLG0Xo5Ha1YPIM72EgsFh\", \"secret\": \"d3406669af297a054aef8a8e0d1529ac\"}";
		parse(reqstr);
		System.out.println("无参构造");
		//parse(reqstr, token);
	}

}
