/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.config;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ontoffline.supermall.mp.component.WxMaInRedisConfig;
import com.ontoffline.supermall.mp.component.WxMaServiceClusterImpl;

/**
 * @author wangylontoffline
 */

@Configuration
@AllArgsConstructor
@ConditionalOnClass(WxMaService.class)
public class WxMaConfiguration {


	@Autowired
    private WxMaInRedisConfig wxMaInRedisConfig;

	@Autowired
    private RedissonClient redissonClient;

    @Bean
    public WxMaService wxMaService() {
        WxMaServiceClusterImpl service = new WxMaServiceClusterImpl();
        service.setWxMaConfig(wxMaInRedisConfig);
        service.setRedissonClient(redissonClient);
        return service;
    }


}
