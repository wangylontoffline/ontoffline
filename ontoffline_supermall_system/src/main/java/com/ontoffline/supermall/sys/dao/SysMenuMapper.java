/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.sys.model.SysMenu;

import java.util.List;

/**
 * @author wangylontoffline
 */

public interface SysMenuMapper extends BaseMapper<SysMenu>{
	
	/**
	 * 根据角色id获取菜单列表
	 * @param roleId 角色id
	 * @return 菜单id列表
	 */
	List<Long> listMenuIdByRoleId(Long roleId);

	/**
	 * 查询用户的所有菜单ID
	 * @param userId 用户id
	 * @return 该用户所有可用的菜单
	 */
	List<SysMenu> listMenuByUserId(Long userId);
	
	/**
	 * 获取系统的所有菜单
	 * @return 系统的所有菜单
	 */
	List<SysMenu> listMenu();

	/**
	 * 获取简单的menu tree 用于在 ele-ui tree中显示，根据orderNum排序
	 * @return ztreeDto列表
	 */
	List<SysMenu> listSimpleMenuNoButton();

	/**
	 * 获取一级菜单
	 * @return 一级菜单列表
	 */
	List<SysMenu> listRootMenu();
	
	/**
	 * 根据一级菜单id 获取二级菜单
	 * @return 二级菜单列表
	 */
	List<SysMenu> listChildrenMenuByParentId(Long parentId);

	List<SysMenu> listMenuAndBtn();
}
