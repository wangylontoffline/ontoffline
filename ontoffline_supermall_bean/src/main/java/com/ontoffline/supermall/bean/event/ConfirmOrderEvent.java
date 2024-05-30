/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.event;


import com.ontoffline.supermall.bean.app.dto.ShopCartDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartOrderMergerDto;
import com.ontoffline.supermall.bean.app.param.OrderParam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class ConfirmOrderEvent {

	
    public ConfirmOrderEvent(ShopCartOrderDto shopCartOrderDto, OrderParam orderParam,
			List<ShopCartItemDto> shopCartItems) {
		super();
		this.shopCartOrderDto = shopCartOrderDto;
		this.orderParam = orderParam;
		this.shopCartItems = shopCartItems;
	}

	/**
     * 购物车已经组装好的店铺订单信息
     */
    private ShopCartOrderDto shopCartOrderDto;

    /**
     * 下单请求的参数
     */
    private OrderParam orderParam;

    /**
     * 店铺中的所有商品项
     */
    private List<ShopCartItemDto> shopCartItems;

	public ShopCartOrderDto getShopCartOrderDto() {
		return shopCartOrderDto;
	}

	public void setShopCartOrderDto(ShopCartOrderDto shopCartOrderDto) {
		this.shopCartOrderDto = shopCartOrderDto;
	}

	public OrderParam getOrderParam() {
		return orderParam;
	}

	public void setOrderParam(OrderParam orderParam) {
		this.orderParam = orderParam;
	}

	public List<ShopCartItemDto> getShopCartItems() {
		return shopCartItems;
	}

	public void setShopCartItems(List<ShopCartItemDto> shopCartItems) {
		this.shopCartItems = shopCartItems;
	}
    
    
}
