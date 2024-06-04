/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.sys.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.sys.dao.SysRoleMapper;
import com.ontoffline.supermall.sys.dao.SysRoleMenuMapper;
import com.ontoffline.supermall.sys.dao.SysUserRoleMapper;
import com.ontoffline.supermall.sys.model.SysRole;
import com.ontoffline.supermall.sys.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Service("sysRoleService")
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	private SysRoleMenuMapper sysRoleMenuMapper;
	private SysUserRoleMapper sysUserRoleMapper;
	private SysRoleMapper sysRoleMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRoleAndRoleMenu(SysRole role) {
		role.setCreateTime(new Date());
		this.save(role);
		if (CollectionUtil.isEmpty(role.getMenuIdList())) {
			return;
		}


		//保存角色与菜单关系
		sysRoleMenuMapper.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRoleAndRoleMenu(SysRole role) {
		// 更新角色
		sysRoleMapper.updateById(role);
		//先删除角色与菜单关系
		sysRoleMenuMapper.deleteBatch(new Long[]{role.getRoleId()});
		if (CollectionUtil.isEmpty(role.getMenuIdList())) {
			return;
		}
		//保存角色与菜单关系
		sysRoleMenuMapper.insertRoleAndRoleMenu(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		sysRoleMapper.deleteBatch(roleIds);

		//删除角色与菜单关联
		sysRoleMenuMapper.deleteBatch(roleIds);

		//删除角色与用户关联
		sysUserRoleMapper.deleteBatch(roleIds);
	}
	@Override
	public List<Long> listRoleIdByUserId(Long userId) {
		return sysRoleMapper.listRoleIdByUserId(userId);
	}

}
