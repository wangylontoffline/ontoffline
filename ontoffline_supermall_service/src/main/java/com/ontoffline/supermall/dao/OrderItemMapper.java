/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.app.dto.OrderItemDto;
import com.ontoffline.supermall.bean.model.OrderItem;

/**
 * @author wangylontoffline
 */

public interface OrderItemMapper extends BaseMapper<OrderItem> {

	List<OrderItem> listByOrderNumber(@Param("orderNumber") String orderNumber);
	
	void insertBatch(List<OrderItem> orderItems);
	
//	List<OrderItem> getPayByOrderNumber(@Param("orderNumber") String orderNumber);

}