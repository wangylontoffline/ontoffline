/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.config.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangylontoffline
 */

@Data
@Component
@PropertySource("classpath:mp.properties")
@ConfigurationProperties(prefix = "mp")
public class WxMp {
    /**
     * 设置微信公众号的appid
     */
    private String appid;

    /**
     * 设置微信公众号的Secret
     */
    private String secret;
    
    /**
     * 微信公众号消息加解密token
     */
    private String token;
    
    /**
     * 微信公众号消息加解密aesKey
     */
    private String aesKey;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
    
    
}
