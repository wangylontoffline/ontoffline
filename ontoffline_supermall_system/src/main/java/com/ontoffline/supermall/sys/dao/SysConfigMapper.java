/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.sys.model.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface SysConfigMapper extends BaseMapper<SysConfig>  {

	/**
	 * 根据key，查询系统配置信息
	 * @param key key
	 * @return SysConfig
	 */
	SysConfig queryByKey(String key);

	/**
	 * 根据key，更新value
	 * @param key
	 * @param value
	 * @return 更新成功条数
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);

	/**
	 * 根据key查询系统配置信息
	 * @param key
	 * @return 系统配置信息列表
	 */
	List<SysConfig> selectByKeyLike(String key);

	/**
	 * 批量删除系统配置
	 * @param ids 系统配置信息数组
	 */
	void deleteBatch(@Param("ids") Long[] ids);
	
}
