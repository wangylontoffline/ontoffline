/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import com.ontoffline.supermall.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ontoffline.supermall.bean.app.param.SendSmsParam;
import com.ontoffline.supermall.bean.enums.SmsType;
import com.ontoffline.supermall.service.SmsLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/sms")
@Api(tags="发送验证码接口")
public class SmsController {

	@Autowired
	private SmsLogService smsLogService;
    /**
     * 发送验证码接口
     */
    @PostMapping("/send")
    @ApiOperation(value="发送验证码", notes="用户的发送验证码")
    public ResponseEntity<Void> audit(@RequestBody SendSmsParam sendSmsParam) {
		String userId = SecurityUtils.getUser().getUserId();
		smsLogService.sendSms(SmsType.VALID, userId, sendSmsParam.getMobile(),Maps.newHashMap());
		
		return ResponseEntity.ok().build();
    }
}
