/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import cn.hutool.core.util.StrUtil;
import com.ontoffline.supermall.common.util.CacheManagerUtil;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.service.OntofflineUser;
import com.ontoffline.supermall.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.app.dto.UserDto;
import com.ontoffline.supermall.bean.app.param.UserInfoParam;
import com.ontoffline.supermall.bean.model.User;
import com.ontoffline.supermall.service.UserService;

import cn.hutool.extra.emoji.EmojiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/user")
@Api(tags="用户接口")
@AllArgsConstructor
public class UserController {

	private UserService userService;
    
	private MapperFacade mapperFacade;

	private CacheManagerUtil cacheManagerUtil;
    /**
     * 查看用户接口
     */
    @GetMapping("/userInfo")
    @ApiOperation(value="查看用户信息", notes="根据用户ID（userId）获取用户信息")
    public ResponseEntity<UserDto> userInfo() {
    	String userId = SecurityUtils.getUser().getUserId();
    	User user = userService.getById(userId);
    	UserDto userDto = mapperFacade.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }
    
    @PutMapping("/setUserInfo")
    @ApiOperation(value="设置用户信息", notes="设置用户信息")
    public ResponseEntity<Void> setUserInfo(@RequestBody UserInfoParam userInfoParam) {
    	String userId = SecurityUtils.getUser().getUserId();
    	User user = new User();
    	user.setUserId(userId);
    	user.setPic(userInfoParam.getAvatarUrl());
    	user.setNickName(EmojiUtil.toAlias(userInfoParam.getNickName()));
    	userService.updateById(user);
		String cacheKey = App.MINI.value() + StrUtil.COLON + SecurityUtils.getUser().getBizUserId();
		cacheManagerUtil.evictCache("yami_user", cacheKey);
        return ResponseEntity.ok(null);
    }
}
