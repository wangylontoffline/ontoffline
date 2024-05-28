/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.test;


import com.ontoffline.supermall.api.security.MiniAppAuthenticationToken;
import com.ontoffline.supermall.common.util.Json;

/**
 * @author wangylontoffline
 */

public class JsonUtilTest {

	public JsonUtilTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static MiniAppAuthenticationToken parse(String authenticationTokenStr) {
		MiniAppAuthenticationToken authRequest = Json.parseObject(authenticationTokenStr, MiniAppAuthenticationToken.class);
        return authRequest;
	}

	public static void main(String[] args) {
        String jstr = "{\"grant_type\": \"mini_app\", \"appid\": \"wx86a3910ee376619b\",\"code\": \"JSCODE\", \"secret\": \"d3406669af297a054aef8a8e0d1529ac\"}";
        parse(jstr);
	}

}
