/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.admin.security;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ontoffline.supermall.common.util.CacheManagerUtil;
import com.ontoffline.supermall.sys.constant.Constant;
import com.ontoffline.supermall.security.enums.App;
import com.ontoffline.supermall.security.exception.UsernameNotFoundExceptionBase;
import com.ontoffline.supermall.security.model.AppConnect;
import com.ontoffline.supermall.security.service.OntofflineSysUser;
import com.ontoffline.supermall.security.service.OntofflineUser;
import com.ontoffline.supermall.security.service.OntofflineUserDetailsService;
import com.ontoffline.supermall.sys.dao.SysMenuMapper;
import com.ontoffline.supermall.sys.dao.SysUserMapper;
import com.ontoffline.supermall.sys.model.SysMenu;
import com.ontoffline.supermall.sys.model.SysUser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangylontoffline
 */

@Slf4j
@Service
@AllArgsConstructor
public class OntofflineSysUserDetailsServiceImpl implements OntofflineUserDetailsService {
	private SysMenuMapper sysMenuMapper;
	private SysUserMapper sysUserMapper;
	private CacheManagerUtil cacheManagerUtil;

	/**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundExceptionBase
	 */
	@Override
	@SneakyThrows
	public OntofflineSysUser loadUserByUsername(String username) {
		return getUserDetails(username);
	}


	/**
	 * 构建userdetails
	 *
	 * @param username 用户名称
	 * @return
	 */
	private OntofflineSysUser getUserDetails(String username) {
		SysUser sysUser = sysUserMapper.selectByUsername(username);

		if (sysUser == null) {
			throw new UsernameNotFoundExceptionBase("用户不存在");
		}

		Collection<? extends GrantedAuthority> authorities
				= AuthorityUtils.createAuthorityList(getUserPermissions(sysUser.getUserId()).toArray(new String[0]));
		// 构造security用户
		return new OntofflineSysUser(sysUser.getUserId(), sysUser.getShopId(), sysUser.getUsername(), sysUser.getPassword(), sysUser.getStatus() == 1,
				true, true, true , authorities);
	}

	private Set<String> getUserPermissions(Long userId) {
		List<String> permsList;

		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN_ID){
			List<SysMenu> menuList = sysMenuMapper.selectList(Wrappers.emptyWrapper());


			permsList = menuList.stream().map(SysMenu::getPerms).collect(Collectors.toList());
		}else{
			permsList = sysUserMapper.queryAllPerms(userId);
		}


		Set<String> permsSet = permsList.stream().flatMap((perms)->{
					if (StrUtil.isBlank(perms)) {
						return null;
					}
					return Arrays.stream(perms.trim().split(","));
				}
		).collect(Collectors.toSet());
		return permsSet;
	}

	@Override
	public OntofflineUser loadUserByAppIdAndBizUserId(App app, String bizUserId) {
		return null;
	}

	@Override
	public void insertUserIfNecessary(AppConnect appConnect) {

	}
}
