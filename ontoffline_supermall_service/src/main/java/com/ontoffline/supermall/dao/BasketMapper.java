/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ontoffline.supermall.bean.app.dto.BasketItemDto;
import com.ontoffline.supermall.bean.app.dto.OrderItemDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;
import com.ontoffline.supermall.bean.app.param.ShopCartParam;
import com.ontoffline.supermall.bean.model.Basket;

/**
 * @author wangylontoffline
 */

public interface BasketMapper extends BaseMapper<Basket> {

    List<ShopCartItemDto> getShopCartItems(@Param("userId") String userId);

    void deleteShopCartItemsByBasketIds(@Param("userId") String userId, @Param("basketIds") List<Long> basketIds);

    void deleteAllShopCartItems(@Param("userId") String userId);
    
    List<ShopCartItemDto> getShopCartExpiryItems(@Param("userId") String userId);

    void cleanExpiryProdList(@Param("userId") String userId);
    
    List<ShopCartItemDto> shopCartItemDtoList(@Param("basketIdList")List<Long> basketIdList);

    void updateDiscountItemId(@Param("userId")String userId, @Param("basketIdShopCartParamMap") Map<Long, ShopCartParam> basketIdShopCartParamMap);

    List<String> listUserIdByProdId(@Param("prodId")Long prodId);
    
}
