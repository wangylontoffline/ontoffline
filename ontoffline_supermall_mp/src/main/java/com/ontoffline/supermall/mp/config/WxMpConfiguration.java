/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.mp.config;


import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ontoffline.supermall.mp.component.WxMpInRedisConfigStorage;
import com.ontoffline.supermall.mp.component.WxMpServiceClusterImpl;
import com.ontoffline.supermall.mp.handler.MenuHandler;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * @author wangylontoffline
 */

@Configuration
@AllArgsConstructor
@ConditionalOnClass(WxMpService.class)
public class WxMpConfiguration {

    @Autowired
	private MenuHandler menuHandler;
    @Autowired
    private WxMpInRedisConfigStorage wxMpInRedisConfigStorage;
    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public WxMpService wxMpService() {
        WxMpServiceClusterImpl service = new WxMpServiceClusterImpl();
        service.setWxMpConfigStorage(wxMpInRedisConfigStorage);
        service.setRedissonClient(redissonClient);
        return service;
    }

    @Bean
    public WxMpMessageRouter messageRouter() {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService());

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.CLICK).handler(this.menuHandler).end();


        return newRouter;
    }

}
