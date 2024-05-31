/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.common.config;


import cn.hutool.crypto.symmetric.AES;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ontoffline.supermall.common.bean.ALiDaYu;
import com.ontoffline.supermall.common.bean.Qiniu;

/**
 * @author wangylontoffline
 */

@Configuration
@AllArgsConstructor
public class ShopBeanConfig {

	@Autowired
	private ShopBasicConfig shopBasicConfig;

    @Bean
    public Qiniu qiniu() {
    	return shopBasicConfig.getQiniu();
    }

    @Bean
    public AES tokenAes() {
    	return new AES(shopBasicConfig.getTokenAesKey().getBytes());
    }

    @Bean
    public ALiDaYu aLiDaYu () {
    	return shopBasicConfig.getALiDaYu();
    }
}
