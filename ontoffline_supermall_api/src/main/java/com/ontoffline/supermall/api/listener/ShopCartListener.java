/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.api.listener;


import com.google.common.collect.Lists;
import com.ontoffline.supermall.bean.app.dto.ShopCartDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDiscountDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;
import com.ontoffline.supermall.bean.event.ShopCartEvent;
import com.ontoffline.supermall.bean.order.ShopCartEventOrder;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Component("defaultShopCartListener")
public class ShopCartListener {

    /**
     * 将店铺下的所有商品归属到该店铺的购物车当中
     * @param event#getShopCart() 购物车
     * @param event#shopCartItemDtoList 该购物车的商品
     * @return 是否继续组装
     */
    @EventListener(ShopCartEvent.class)
    @Order(ShopCartEventOrder.DEFAULT)
    public void defaultShopCartEvent(ShopCartEvent event) {
        ShopCartDto shopCart = event.getShopCartDto();
        List<ShopCartItemDto> shopCartItemDtoList = event.getShopCartItemDtoList();
        // 对数据进行组装
        List<ShopCartItemDiscountDto> shopCartItemDiscountDtoList = Lists.newArrayList();
        ShopCartItemDiscountDto shopCartItemDiscountDto = new ShopCartItemDiscountDto();

        shopCartItemDiscountDto.setShopCartItems(shopCartItemDtoList);
        shopCartItemDiscountDtoList.add(shopCartItemDiscountDto);

        shopCart.setShopCartItemDiscounts(shopCartItemDiscountDtoList);
    }

}
