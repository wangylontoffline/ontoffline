/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ontoffline.supermall.common.bean.ALiDaYu;
import com.ontoffline.supermall.common.bean.Qiniu;

/**
 * @author wangylontoffline
 */

@Data
@Component
@PropertySource("classpath:supermall.properties")
@ConfigurationProperties(prefix = "supermall")
public class ShopBasicConfig {

	/**
	 * 七牛云的配置信息
	 */
	private Qiniu qiniu;

	/**
	 * 阿里大于短信平台
	 */
	private ALiDaYu aLiDaYu;

	/**
	 * 用于加解密token的密钥
	 */
	private String tokenAesKey;

	public Qiniu getQiniu() {
		return qiniu;
	}

	public void setQiniu(Qiniu qiniu) {
		this.qiniu = qiniu;
	}

	public ALiDaYu getALiDaYu() {
		return aLiDaYu;
	}

	public void setALiDaYu(ALiDaYu aLiDaYu) {
		this.aLiDaYu = aLiDaYu;
	}

	public String getTokenAesKey() {
		return tokenAesKey;
	}

	public void setTokenAesKey(String tokenAesKey) {
		this.tokenAesKey = tokenAesKey;
	}

}
