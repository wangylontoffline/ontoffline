/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */
package com.ontoffline.supermall.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.sys.model.SysConfig;

/**
 * @author wangylontoffline
 */

public interface SysConfigService extends IService<SysConfig>  {

	/**
	 * 根据key，更新value
	 * @param key 参数key
	 * @param value 参数value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 * @param ids 配置项id列表
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * @param key 参数key
	 * @return value
	 */
	public String getValue(String key);

}
