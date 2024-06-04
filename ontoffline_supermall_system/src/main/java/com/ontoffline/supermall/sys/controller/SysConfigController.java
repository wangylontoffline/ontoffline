/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.controller;


import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ontoffline.supermall.sys.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ontoffline.supermall.common.util.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.common.annotation.SysLog;
import com.ontoffline.supermall.sys.service.SysConfigService;

import cn.hutool.core.util.StrUtil;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/sys/config")
public class SysConfigController{
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('sys:config:page')")
	public ResponseEntity<IPage<SysConfig>> page(String paramKey,PageParam<SysConfig> page){
		IPage<SysConfig> sysConfigs = sysConfigService.page(page, new LambdaQueryWrapper<SysConfig>().like(StrUtil.isNotBlank(paramKey),SysConfig::getParamKey,paramKey));
		return ResponseEntity.ok(sysConfigs);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@PreAuthorize("@pms.hasPermission('sys:config:info')")
	public ResponseEntity<SysConfig> info(@PathVariable("id") Long id){
		SysConfig config = sysConfigService.getById(id);
		return ResponseEntity.ok(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys:config:save')")
	public ResponseEntity<Void> save(@RequestBody @Valid SysConfig config){
		sysConfigService.save(config);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys:config:update')")
	public ResponseEntity<Void> update(@RequestBody @Valid SysConfig config){
		sysConfigService.updateById(config);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys:config:delete')")
	public ResponseEntity<Void> delete(@RequestBody Long[] configIds){
		sysConfigService.deleteBatch(configIds);
		return ResponseEntity.ok().build();
	}

}
