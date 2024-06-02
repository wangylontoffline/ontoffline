/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.service;


import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.exception.UsernameNotFoundExceptionBase;
import com.ontoffline.supermall.security.model.AppConnect;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author wangylontoffline
 */

public interface OntofflineUserDetailsService extends UserDetailsService {

	/**
	 * 获取前端登陆的用户信息
	 *
	 * @param app 所登陆的应用
	 * @param bizUserId openId
	 * @return UserDetails
	 * @throws UsernameNotFoundExceptionBase
	 */
	OntofflineUser loadUserByAppIdAndBizUserId(App app, String bizUserId);

	/**
	 * 如果必要的话，插入新增用户
	 * @param appConnect
	 */
	void insertUserIfNecessary(AppConnect appConnect);
}
