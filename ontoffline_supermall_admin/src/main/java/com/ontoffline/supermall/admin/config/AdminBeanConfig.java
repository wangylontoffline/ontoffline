/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.config;


import cn.hutool.core.lang.Snowflake;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangylontoffline
 */

@Configuration
@AllArgsConstructor
public class AdminBeanConfig {

    private AdminConfig adminConfig;

    @Bean
    public Snowflake snowflake() {
    	return new Snowflake(adminConfig.getWorkerId(), adminConfig.getDatacenterId());
    }
}
