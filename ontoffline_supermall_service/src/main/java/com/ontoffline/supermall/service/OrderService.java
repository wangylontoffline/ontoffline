/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.app.dto.OrderCountData;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderMergerDto;
import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.bean.param.OrderParam;

import cn.hutool.core.date.DateTime;

/**
 * @author wangylontoffline
 */

public interface OrderService extends IService<Order> {


    Order getOrderByOrderNumber(String orderNumber);

    ShopCartOrderMergerDto putConfirmOrderCache(String userId ,ShopCartOrderMergerDto shopCartOrderMergerDto);

    ShopCartOrderMergerDto getConfirmOrderCache(String userId);

    void removeConfirmOrderCache(String userId);

    List<Order> submit(String userId, ShopCartOrderMergerDto mergerOrder);
    
    void updateOrderStatus(String userId, Order order);

    void delivery(Order order);

    List<Order> listOrderAndOrderItems(Integer orderStatus, DateTime lessThanUpdateTime);

    void cancelOrders(List<Order> orders);

    void confirmOrder(List<Order> orders);

    List<Order> listOrdersDetialByOrder(Order order, Date startTime, Date endTime);

    IPage<Order> pageOrdersDetialByOrderParam(Page<Order> page, OrderParam orderParam);

    void deleteOrders(List<Order> orders);

    OrderCountData getOrderCount(String userId);
}
