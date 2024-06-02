/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.security.dao.AppConnectMapper;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.model.AppConnect;
import com.ontoffline.supermall.security.service.AppConnectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class AppConnectServiceImpl extends ServiceImpl<AppConnectMapper, AppConnect> implements AppConnectService {

    @Autowired
    private AppConnectMapper appConnectMapper;

	/**
	 * YamiUserServiceImpl#insertUserIfNecessary 将会清楚该缓存信息
	 * @param bizUserId
	 * @param app
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = "AppConnect", key = "#app.value() + ':' + #bizUserId")
	public AppConnect getByBizUserId(String bizUserId, App app) {
		return appConnectMapper.getByBizUserId(bizUserId, app.value());
	}

}
