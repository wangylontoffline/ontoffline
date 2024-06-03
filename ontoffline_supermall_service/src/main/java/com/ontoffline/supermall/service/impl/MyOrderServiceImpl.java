/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ontoffline.supermall.bean.app.dto.MyOrderDto;
import com.ontoffline.supermall.bean.app.dto.MyOrderItemDto;
import com.ontoffline.supermall.bean.app.dto.ProductDto;
import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.dao.OrderMapper;
import com.ontoffline.supermall.service.MyOrderService;
import com.ontoffline.supermall.common.util.PageAdapter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangylontoffline
 */

@Service
public class MyOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements MyOrderService {

    @Autowired
    private OrderMapper orderMapper;

    private static final Logger log = LoggerFactory.getLogger(MyOrderServiceImpl.class);


    @Override
    public IPage<MyOrderDto> pageMyOrderByUserIdAndStatus(Page<MyOrderDto> page, String userId, Integer status) {
        page.setRecords(orderMapper.listMyOrderByUserIdAndStatus(new PageAdapter(page), userId, status));
        page.setTotal(orderMapper.countMyOrderByUserIdAndStatus(userId, status));
        return page;
    }

}
