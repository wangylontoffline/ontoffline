/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.security.dao;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.security.model.AppConnect;

/**
 * @author wangylontoffline
 */

public interface AppConnectMapper extends BaseMapper<AppConnect> {

	AppConnect getByBizUserId(@Param("bizUserId") String bizUserId, @Param("appId") Integer appId);

	AppConnect getByUserId(@Param("userId") String userId, @Param("appId") Integer appId);

    String getUserIdByUnionId(@Param("bizUnionId") String bizUnionId);
}