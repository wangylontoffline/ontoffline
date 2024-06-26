/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.sys.model.SysUser;

/**
 * @author wangylontoffline
 */

public interface SysUserService extends IService<SysUser> {

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param newPassword  新密码
	 */
	void updatePasswordByUserId(Long userId, String newPassword);

	/**
	 * 保存用户与用户角色关系
	 * @param user
	 */
	void saveUserAndUserRole(SysUser user);


	/**
	 * 更新用户与用户角色关系
	 * @param user
	 */
	void updateUserAndUserRole(SysUser user);

	/**
	 * 根据用户id 批量删除用户
	 * @param userIds
	 */
	void deleteBatch(Long[] userIds,Long shopId);

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	SysUser getByUserName(String username);

	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return
	 */
	SysUser getSysUserById(Long userId);

}
