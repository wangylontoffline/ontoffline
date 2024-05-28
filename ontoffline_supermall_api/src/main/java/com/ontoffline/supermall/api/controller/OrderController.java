/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.controller;


import java.util.*;

import javax.validation.Valid;

import com.ontoffline.supermall.bean.app.dto.*;
import com.ontoffline.supermall.bean.event.ConfirmOrderEvent;
import com.ontoffline.supermall.common.exception.YamiShopBindException;
import com.ontoffline.supermall.security.util.SecurityUtils;
import com.ontoffline.supermall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontoffline.supermall.bean.app.param.OrderParam;
import com.ontoffline.supermall.bean.app.param.OrderShopParam;
import com.ontoffline.supermall.bean.app.param.SubmitOrderParam;
import com.ontoffline.supermall.bean.model.Order;
import com.ontoffline.supermall.bean.model.UserAddr;
import com.ontoffline.supermall.bean.param.OrderStatusParam;
import com.ontoffline.supermall.common.util.Arith;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;

/**
 * @author wangylontoffline
 */

@RestController
@RequestMapping("/p/order")
@Api(tags = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private SkuService skuService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserAddrService userAddrService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 生成订单
     */
    @PostMapping("/confirm")
    @ApiOperation(value = "结算，生成订单信息", notes = "传入下单所需要的参数进行下单")
    public ResponseEntity<ShopCartOrderMergerDto> confirm(@Valid @RequestBody OrderParam orderParam) {
        String userId = SecurityUtils.getUser().getUserId();

        // 订单的地址信息
        UserAddr userAddr = userAddrService.getUserAddrByUserId(orderParam.getAddrId(), userId);
        UserAddrDto userAddrDto = mapperFacade.map(userAddr, UserAddrDto.class);


        // 组装获取用户提交的购物车商品项
        List<ShopCartItemDto> shopCartItems = basketService.getShopCartItemsByOrderItems(orderParam.getBasketIds(),orderParam.getOrderItem(),userId);

        if (CollectionUtil.isEmpty(shopCartItems)) {
            throw new YamiShopBindException("请选择您需要的商品加入购物车");
        }

        // 根据店铺组装购车中的商品信息，返回每个店铺中的购物车商品信息
        List<ShopCartDto> shopCarts = basketService.getShopCarts(shopCartItems);

        // 将要返回给前端的完整的订单信息
        ShopCartOrderMergerDto shopCartOrderMergerDto = new ShopCartOrderMergerDto();

        shopCartOrderMergerDto.setUserAddr(userAddrDto);

        // 所有店铺的订单信息
        List<ShopCartOrderDto> shopCartOrders = new ArrayList<>();

        double actualTotal = 0.0;
        double total = 0.0;
        int totalCount = 0;
        double orderReduce = 0.0;
//        List<CouponOrderDto> coupons = new ArrayList<>();
        for (ShopCartDto shopCart : shopCarts) {

            // 每个店铺的订单信息
            ShopCartOrderDto shopCartOrder = new ShopCartOrderDto();
            shopCartOrder.setShopId(shopCart.getShopId());
            shopCartOrder.setShopName(shopCart.getShopName());


            List<ShopCartItemDiscountDto> shopCartItemDiscounts = shopCart.getShopCartItemDiscounts();

            // 店铺中的所有商品项信息
            List<ShopCartItemDto> shopAllShopCartItems = new ArrayList<>();
            for (ShopCartItemDiscountDto shopCartItemDiscount : shopCartItemDiscounts) {
                List<ShopCartItemDto> discountShopCartItems = shopCartItemDiscount.getShopCartItems();
                shopAllShopCartItems.addAll(discountShopCartItems);
            }

            shopCartOrder.setShopCartItemDiscounts(shopCartItemDiscounts);

            applicationContext.publishEvent(new ConfirmOrderEvent(shopCartOrder,orderParam,shopAllShopCartItems));

            actualTotal = Arith.add(actualTotal,shopCartOrder.getActualTotal());
            total = Arith.add(total,shopCartOrder.getTotal());
            totalCount = totalCount + shopCartOrder.getTotalCount();
            orderReduce = Arith.add(orderReduce,shopCartOrder.getShopReduce());
            shopCartOrders.add(shopCartOrder);


        }

        shopCartOrderMergerDto.setActualTotal(actualTotal);
        shopCartOrderMergerDto.setTotal(total);
        shopCartOrderMergerDto.setTotalCount(totalCount);
        shopCartOrderMergerDto.setShopCartOrders(shopCartOrders);
        shopCartOrderMergerDto.setOrderReduce(orderReduce);

        orderService.putConfirmOrderCache(userId,shopCartOrderMergerDto);

        return ResponseEntity.ok(shopCartOrderMergerDto);
    }

    /**
     * 购物车/立即购买  提交订单,根据店铺拆单
     */
    @PostMapping("/submit")
    @ApiOperation(value = "提交订单，返回支付流水号", notes = "根据传入的参数判断是否为购物车提交订单，同时对购物车进行删除，用户开始进行支付")
    public ResponseEntity<OrderNumbersDto> submitOrders(@Valid @RequestBody SubmitOrderParam submitOrderParam) {
        String userId = SecurityUtils.getUser().getUserId();
        ShopCartOrderMergerDto mergerOrder = orderService.getConfirmOrderCache(userId);
        if (mergerOrder == null) {
            throw new YamiShopBindException("订单已过期，请重新下单");
        }

        List<OrderShopParam> orderShopParams = submitOrderParam.getOrderShopParam();

        List<ShopCartOrderDto> shopCartOrders = mergerOrder.getShopCartOrders();
        // 设置备注
        if (CollectionUtil.isNotEmpty(orderShopParams)) {
            for (ShopCartOrderDto shopCartOrder : shopCartOrders) {
                for (OrderShopParam orderShopParam : orderShopParams) {
                    if (Objects.equals(shopCartOrder.getShopId(), orderShopParam.getShopId())) {
                        shopCartOrder.setRemarks(orderShopParam.getRemarks());
                    }
                }
            }
        }

        List<Order> orders = orderService.submit(userId,mergerOrder);



        StringBuilder orderNumbers = new StringBuilder();
        for (Order order : orders) {
            orderNumbers.append(order.getOrderNumber()).append(",");
        }
        orderNumbers.deleteCharAt(orderNumbers.length() - 1);

        boolean isShopCartOrder = false;
        // 移除缓存
        for (ShopCartOrderDto shopCartOrder : shopCartOrders) {
            for (ShopCartItemDiscountDto shopCartItemDiscount : shopCartOrder.getShopCartItemDiscounts()) {
                for (ShopCartItemDto shopCartItem : shopCartItemDiscount.getShopCartItems()) {
                    Long basketId = shopCartItem.getBasketId();
                    if (basketId != null && basketId != 0) {
                        isShopCartOrder = true;
                    }
                    skuService.removeSkuCacheBySkuId(shopCartItem.getSkuId(),shopCartItem.getProdId());
                    productService.removeProductCacheByProdId(shopCartItem.getProdId());
                }
            }
        }
        // 购物车提交订单时(即有购物车ID时)
        if (isShopCartOrder) {
            basketService.removeShopCartItemsCacheByUserId(userId);
        }
        orderService.removeConfirmOrderCache(userId);
        return ResponseEntity.ok(new OrderNumbersDto(orderNumbers.toString()));
    }
    
    /**
     * 支付成功后，更新订单状态（待发货）
     */
    @PostMapping("/paySuccess")
    @ApiOperation(value = "支付成功后，更新订单状态（待发货）", notes = "根据订单号、支付成功状态，设置待发货状态")
    public ResponseEntity<OrderNumbersDto> paySuccessOrders(@Valid @RequestBody OrderStatusParam orderStatusParam) {
    	System.out.println("oto solution : pay success process");
    	String userId = orderStatusParam.getUserId();
    	if(userId == null || userId.equals(""))
    	userId = SecurityUtils.getUser().getUserId();
        ShopCartOrderMergerDto mergerOrder = orderService.getConfirmOrderCache(userId);

        if (mergerOrder == null) {
            throw new YamiShopBindException("订单已过期，请重新下单");
        }
        Order order = new Order();
        order.setOrderNumber(orderStatusParam.getOrderNumbers());
        orderService.updateOrderStatus(userId, order);
        return ResponseEntity.ok(new OrderNumbersDto(order.getOrderNumber().toString()));
    }

}
