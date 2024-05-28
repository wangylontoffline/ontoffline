/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import com.ontoffline.supermall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.app.dto.DeliveryDto;
import com.ontoffline.supermall.bean.model.Delivery;
import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.common.util.Json;
import com.ontoffline.supermall.service.DeliveryService;

import cn.hutool.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/delivery")
@Api(tags="查看物流接口")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private OrderService orderService;

    /**
     * 查看物流接口
     */
    @GetMapping("/check")
    @ApiOperation(value="查看物流", notes="根据订单号查看物流")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<DeliveryDto> checkDelivery(String orderNumber) {

    	Order order = orderService.getOrderByOrderNumber(orderNumber);
    	Delivery delivery = deliveryService.getById(order.getDvyId());
    	String url = delivery.getQueryUrl().replace("{dvyFlowId}", order.getDvyFlowId());
    	String deliveryJson = HttpUtil.get(url);

    	DeliveryDto deliveryDto = Json.parseObject(deliveryJson, DeliveryDto.class);
    	deliveryDto.setDvyFlowId(order.getDvyFlowId());
    	deliveryDto.setCompanyHomeUrl(delivery.getCompanyHomeUrl());
    	deliveryDto.setCompanyName(delivery.getDvyName());
        return ResponseEntity.ok(deliveryDto);
    }
}
