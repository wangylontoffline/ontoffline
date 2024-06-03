/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ontoffline.supermall.bean.app.dto.*;
import com.ontoffline.supermall.bean.app.param.ChangeShopCartParam;
import com.ontoffline.supermall.bean.app.param.OrderItemParam;
import com.ontoffline.supermall.bean.app.param.ShopCartParam;
import com.ontoffline.supermall.bean.model.Basket;

/**
 * @author wangylontoffline
 */

public interface BasketService extends IService<Basket> {

    void deleteShopCartItemsByBasketIds(String userId, List<Long> basketIds);

    void addShopCartItem(ChangeShopCartParam param, String userId);

    void updateShopCartItem(Basket basket);

    void deleteAllShopCartItems(String userId);

    List<ShopCartItemDto> getShopCartItems(String userId);

    List<ShopCartItemDto> getShopCartExpiryItems(String userId);

    void cleanExpiryProdList(String userId);

    void updateBasketByShopCartParam(String userId, Map<Long, ShopCartParam> basketIdShopCartParamMap);

    void removeShopCartItemsCacheByUserId(String userId);

    /**
     * 获取购物车中拥有某件商品的用户，用于清除该用户购物车的缓存
     * @param prodId 商品id
     * @return 用户id
     */
    List<String> listUserIdByProdId(Long prodId);

    /**
     * 根据店铺组装购车中的商品信息，返回每个店铺中的购物车商品信息
     * @param shopCartItems 指定的购物项目
     * @return 每个店铺的购物车信息
     */
    List<ShopCartDto> getShopCarts(List<ShopCartItemDto> shopCartItems);

    /**
     * 组装获取用户提交的购物车商品项
     * @param orderItems 提交订单时携带的商品信息
     * @param userId 当前用户的用户id
     * @return 所有的商品购物项
     */
    List<ShopCartItemDto> getShopCartItemsByOrderItems(List<Long> basketId, OrderItemParam orderItem,String userId);

}
