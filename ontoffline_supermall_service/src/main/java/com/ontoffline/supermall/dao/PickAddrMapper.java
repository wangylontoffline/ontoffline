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
import com.ontoffline.supermall.bean.model.PickAddr;

/**
 * @author wangylontoffline
 */

public interface PickAddrMapper extends BaseMapper<PickAddr> {

	void deleteByIds(@Param("ids") Long[] ids);
}