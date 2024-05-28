/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.hutool.core.lang.Snowflake;
import lombok.AllArgsConstructor;

/**
 * @author wangylontoffline
 */

@Configuration
@AllArgsConstructor
public class ApiBeanConfig {

	@Autowired
    private ApiConfig apiConfig;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(apiConfig.getWorkerId(), apiConfig.getDatacenterId());
    }

}
