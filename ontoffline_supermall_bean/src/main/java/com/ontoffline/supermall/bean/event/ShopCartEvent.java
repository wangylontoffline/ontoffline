/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.event;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.ontoffline.supermall.bean.app.dto.ShopCartDto;
import com.ontoffline.supermall.bean.app.dto.ShopCartItemDto;

/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

@Data
public class ShopCartEvent {

    /**
     * 将要组装的单个店铺的店铺信息
     */
    private ShopCartDto shopCartDto;

    /**
     * 该店铺下的所有商品信息
     */
    private List<ShopCartItemDto> shopCartItemDtoList;

	public ShopCartEvent(ShopCartDto shopCartDto, List<ShopCartItemDto> shopCartItemDtoList) {
		super();
		this.shopCartDto = shopCartDto;
		this.shopCartItemDtoList = shopCartItemDtoList;
	}

	public ShopCartDto getShopCartDto() {
		return shopCartDto;
	}

	public void setShopCartDto(ShopCartDto shopCartDto) {
		this.shopCartDto = shopCartDto;
	}

	public List<ShopCartItemDto> getShopCartItemDtoList() {
		return shopCartItemDtoList;
	}

	public void setShopCartItemDtoList(List<ShopCartItemDto> shopCartItemDtoList) {
		this.shopCartItemDtoList = shopCartItemDtoList;
	}
    
    
}
