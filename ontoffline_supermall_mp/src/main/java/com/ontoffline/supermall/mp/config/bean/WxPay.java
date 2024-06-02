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
@PropertySource("classpath:pay.properties")
@ConfigurationProperties(prefix = "pay")
public class WxPay {
    /**
     * 微信支付mchId
     */
    private String mchId;
    
    /**
     * 微信支付mchKey
     */
    private String mchKey;
    
    /**
     * 签名类型
     */
    private String signType;

	/**
	 * 支付证书路径
	 */
	private String keyPath;

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

    
}
