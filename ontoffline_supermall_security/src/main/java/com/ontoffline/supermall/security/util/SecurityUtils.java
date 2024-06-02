/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.util;


import com.ontoffline.supermall.security.exception.UnauthorizedExceptionBase;
import com.ontoffline.supermall.security.service.OntofflineSysUser;
import com.ontoffline.supermall.security.service.OntofflineUser;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author wangylontoffline
 */

@UtilityClass
public class SecurityUtils {
	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public static OntofflineUser getUser() {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof OntofflineUser) {
			return (OntofflineUser) principal;
		}
		throw new UnauthorizedExceptionBase("无法获取普通用户信息");
	}

	/**
	 * 获取系统用户
	 */
	public static OntofflineSysUser getSysUser() {
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof OntofflineSysUser) {
			return (OntofflineSysUser) principal;
		}
		throw new UnauthorizedExceptionBase("无法获取系统用户信息");
	}
}
