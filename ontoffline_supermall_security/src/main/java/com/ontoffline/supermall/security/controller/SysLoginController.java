/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.controller;


import java.awt.*;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ontoffline.supermall.security.constants.SecurityConstants;
import com.ontoffline.supermall.security.service.OntofflineSysUser;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.common.util.RedisUtil;
import com.ontoffline.supermall.common.util.SimpleCaptcha;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wangylontoffline
 */

@Controller
@AllArgsConstructor
@ApiIgnore
public class SysLoginController {

	private CacheManager cacheManager;


	@GetMapping("/captcha.jpg")
	public void login(HttpServletResponse response,String uuid) {
		//定义图形验证码的长、宽、验证码字符数、干扰元素个数
		SimpleCaptcha simpleCaptcha = new SimpleCaptcha(200, 50, 4, 20);
		try {
			simpleCaptcha.write(response.getOutputStream());
			RedisUtil.set(SecurityConstants.SPRING_SECURITY_RESTFUL_IMAGE_CODE+uuid, simpleCaptcha.getCode(), 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出
	 */
	@PostMapping(value = "/sys/logout")
	public ResponseEntity<String> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok().build();
	}

}
