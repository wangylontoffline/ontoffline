/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ontoffline.supermall.sys.model.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.sys.service.SysLogService;

import cn.hutool.core.util.StrUtil;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('sys:log:page')")
	public ResponseEntity<IPage<SysLog>> page(SysLog sysLog,PageParam<SysLog> page){
		IPage<SysLog> sysLogs = sysLogService.page(page,
				new LambdaQueryWrapper<SysLog>()
						.like(StrUtil.isNotBlank(sysLog.getUsername()),SysLog::getUsername, sysLog.getUsername())
						.like(StrUtil.isNotBlank(sysLog.getOperation()), SysLog::getOperation,sysLog.getOperation()));
		return ResponseEntity.ok(sysLogs);
	}
	
}
