/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.security;


import com.ontoffline.supermall.security.token.MyAuthenticationToken;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wangylontoffline
 */

@NoArgsConstructor
public class MiniAppAuthenticationToken extends MyAuthenticationToken {
	
	private String appid;
	
	private String secret;
	
	private String js_code;
	
	private String grant_type;

    public MiniAppAuthenticationToken(UserDetails principal, Object credentials) {
        super(principal, credentials, principal.getAuthorities());
    }

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getJs_code() {
		if(js_code != null && !js_code.equals(""))super.principal = js_code;
		return js_code;
	}

	public void setJs_Code(String js_code) {
		this.js_code = js_code;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
