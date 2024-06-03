/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.model.UserAddr;
import com.ontoffline.supermall.dao.UserAddrMapper;
import com.ontoffline.supermall.service.UserAddrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;

/**
 * @author wangylontoffline
 */

@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {

	@Autowired
	private UserAddrMapper userAddrMapper;

	@Override
	public UserAddr getDefaultUserAddr(String userId) {
		return userAddrMapper.getDefaultUserAddr(userId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateDefaultUserAddr(Long addrId, String userId) {
		userAddrMapper.removeDefaultUserAddr(userId);
		int setCount = userAddrMapper.setDefaultUserAddr(addrId,userId);
		if (setCount == 0) {
			throw new OntofflineSupermallBindException("无法修改用户默认地址，请稍后再试");
		}
	}

    @Override
	@CacheEvict(cacheNames = "UserAddrDto", key = "#userId+':'+#addrId")
    public void removeUserAddrByUserId(Long addrId, String userId) {

    }

	@Override
	@Cacheable(cacheNames = "UserAddrDto", key = "#userId+':'+#addrId")
	public UserAddr getUserAddrByUserId(Long addrId, String userId) {
		if (addrId == 0) {
			return userAddrMapper.getDefaultUserAddr(userId);
		}
		return userAddrMapper.getUserAddrByUserIdAndAddrId(userId, addrId);
	}


}
