/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.model.User;
import com.ontoffline.supermall.bean.vo.UserVO;

import org.apache.ibatis.annotations.Param;

/**
 * @author wangylontoffline
 */

public interface UserMapper extends BaseMapper<User> {

	User getUserByBizUserId(@Param("appId")Integer appId, @Param("bizUserId")String bizUserId);
}
