/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.sys.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface SysUserRoleMapper  extends BaseMapper<SysUserRole> {
	
	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(@Param("roleIds") Long[] roleIds);

	/**
	 * 根据用户id删除用户与角色关系
	 * @param userId
	 */
	void deleteByUserId(Long userId);

	/**
	 * 根据用户id 批量添加用户角色关系
	 * @param userId
	 * @param roleIdList
	 */
	void insertUserAndUserRole(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);
}
