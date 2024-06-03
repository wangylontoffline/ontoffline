/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.UserAddr;

/**
 * @author wangylontoffline
 */

public interface UserAddrMapper extends BaseMapper<UserAddr> {

	UserAddr getDefaultUserAddr(@Param("userId") String userId);

	/**
	 * 移除用户默认地址
	 * @param userId
	 */
	void removeDefaultUserAddr(@Param("userId") String userId);

	/**
	 * 将地址设置为默认地址
	 * @param addrId
	 * @param userId
	 */
	int setDefaultUserAddr(@Param("addrId") Long addrId, @Param("userId") String userId);

	UserAddr getUserAddrByUserIdAndAddrId(@Param("userId") String userId, @Param("addrId") Long addrId);
}