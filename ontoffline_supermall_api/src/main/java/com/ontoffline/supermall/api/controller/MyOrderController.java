/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ontoffline.supermall.common.exception.OntofflineSupermallBindException;
import com.ontoffline.supermall.common.util.PageParam;
import com.ontoffline.supermall.bean.app.dto.*;
import com.ontoffline.supermall.dao.OrderMapper;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.bean.model.OrderItem;
import com.ontoffline.supermall.bean.model.ShopDetail;
import com.ontoffline.supermall.bean.model.UserAddrOrder;
import com.ontoffline.supermall.common.util.Arith;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/myOrder")
@Api(tags = "我的订单接口")
@AllArgsConstructor
public class MyOrderController {

    private OrderService orderService;

    private MapperFacade mapperFacade;

    private UserAddrOrderService userAddrOrderService;

    private ProductService productService;

    private SkuService skuService;

    private MyOrderService myOrderService;

    private ShopDetailService shopDetailService;

    private OrderItemService orderItemService;


    /**
     * 订单详情信息接口
     */
    @GetMapping("/orderDetail")
    @ApiOperation(value = "订单详情信息", notes = "根据订单号获取订单详情信息")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<OrderShopDto> orderDetail(@RequestParam(value = "orderNumber", required = true) String orderNumber) {

        String userId = SecurityUtils.getUser().getUserId();
        OrderShopDto orderShopDto = new OrderShopDto();

        Order order = orderService.getOrderByOrderNumber(orderNumber);

        if (order == null) {
            throw new RuntimeException("该订单不存在");
        }
        if (!Objects.equals(order.getUserId(), userId)) {
            throw new RuntimeException("你没有权限获取该订单信息");
        }

        ShopDetail shopDetail = shopDetailService.getShopDetailByShopId(order.getShopId());
        UserAddrOrder userAddrOrder = userAddrOrderService.getById(order.getAddrOrderId());
        UserAddrDto userAddrDto = mapperFacade.map(userAddrOrder, UserAddrDto.class);
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderNumber(orderNumber);
        List<OrderItemDto> orderItemDtos = mapperFacade.mapAsList(orderItems, OrderItemDto.class);

        orderShopDto.setShopId(shopDetail.getShopId());
        orderShopDto.setShopName(shopDetail.getShopName());
        orderShopDto.setActualTotal(order.getActualTotal());
        orderShopDto.setUserAddrDto(userAddrDto);
        orderShopDto.setOrderItemDtos(orderItemDtos);
        orderShopDto.setTransfee(order.getFreightAmount());
        orderShopDto.setReduceAmount(order.getReduceAmount());
        orderShopDto.setCreateTime(order.getCreateTime());
        orderShopDto.setRemarks(order.getRemarks());
        orderShopDto.setStatus(order.getStatus());

        double total = 0.0;
        Integer totalNum = 0;
        for (OrderItemDto orderItem : orderShopDto.getOrderItemDtos()) {
            total = Arith.add(total, orderItem.getProductTotalAmount());
            totalNum += orderItem.getProdCount();
        }
        orderShopDto.setTotal(total);
        orderShopDto.setTotalNum(totalNum);

        return ResponseEntity.ok(orderShopDto);
    }


    /**
     * 订单列表接口
     */
    @GetMapping("/myOrder")
    @ApiOperation(value = "订单列表信息", notes = "根据订单状态获取订单列表信息，状态为0时获取所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败", required = false, dataType = "Integer")
    })
    public ResponseEntity<IPage<MyOrderDto>> myOrder(@RequestParam(value = "status") Integer status,PageParam<MyOrderDto> page) {

        String userId = SecurityUtils.getUser().getUserId();
        IPage<MyOrderDto> myOrderDtoIpage = myOrderService.pageMyOrderByUserIdAndStatus(page, userId, status);
        return ResponseEntity.ok(myOrderDtoIpage);
    }

    /**
     * 取消订单
     */
    @PutMapping("/cancel/{orderNumber}")
    @ApiOperation(value = "根据订单号取消订单", notes = "根据订单号取消订单")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<String> cancel(@PathVariable("orderNumber") String orderNumber) {
        String userId = SecurityUtils.getUser().getUserId();
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        if (!Objects.equals(order.getUserId(), userId)) {
            throw new OntofflineSupermallBindException("你没有权限获取该订单信息");
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderNumber(orderNumber);
        order.setOrderItems(orderItems);
        // 取消订单
        orderService.cancelOrders(Arrays.asList(order));

        // 清除缓存
        for (OrderItem orderItem : orderItems) {
            productService.removeProductCacheByProdId(orderItem.getProdId());
            skuService.removeSkuCacheBySkuId(orderItem.getSkuId(),orderItem.getProdId());
        }
        return ResponseEntity.ok().build();
    }


    /**
     * 确认收货
     */
    @PutMapping("/receipt/{orderNumber}")
    @ApiOperation(value = "根据订单号确认收货", notes = "根据订单号确认收货")
    public ResponseEntity<String> receipt(@PathVariable("orderNumber") String orderNumber) {
        String userId = SecurityUtils.getUser().getUserId();
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        if (!Objects.equals(order.getUserId(), userId)) {
            throw new OntofflineSupermallBindException("你没有权限获取该订单信息");
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderNumber(orderNumber);
        order.setOrderItems(orderItems);
        // 确认收货
        orderService.confirmOrder(Arrays.asList(order));

        for (OrderItem orderItem : orderItems) {
            productService.removeProductCacheByProdId(orderItem.getProdId());
            skuService.removeSkuCacheBySkuId(orderItem.getSkuId(),orderItem.getProdId());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{orderNumber}")
    @ApiOperation(value = "根据订单号删除订单", notes = "根据订单号删除订单")
    @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String")
    public ResponseEntity<String> delete(@PathVariable("orderNumber") String orderNumber) {
        String userId = SecurityUtils.getUser().getUserId();

        Order order = orderService.getOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new OntofflineSupermallBindException("该订单不存在");
        }
        if (!Objects.equals(order.getUserId(), userId)) {
            throw new OntofflineSupermallBindException("你没有权限获取该订单信息");
        }

        // 删除订单
        orderService.deleteOrders(Arrays.asList(order));

        return ResponseEntity.ok("删除成功");
    }

    /**
     * 获取我的订单订单数量
     */
    @GetMapping("/orderCount")
    @ApiOperation(value = "获取我的订单订单数量", notes = "获取我的订单订单数量")
    public ResponseEntity<OrderCountData> getOrderCount() {
        String userId = SecurityUtils.getUser().getUserId();
        OrderCountData orderCountMap = orderService.getOrderCount(userId);
        return ResponseEntity.ok(orderCountMap);
    }


}
