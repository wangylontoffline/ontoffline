/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.app.dto.OrderItemDto;
import com.ontoffline.supermall.bean.model.OrderItem;
import com.ontoffline.supermall.dao.OrderItemMapper;
import com.ontoffline.supermall.service.OrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

	@Override
	@Cacheable(cacheNames = "OrderItems", key = "#orderNumber")
	public List<OrderItem> getOrderItemsByOrderNumber(String orderNumber) {
		return orderItemMapper.listByOrderNumber(orderNumber);
	}


}
