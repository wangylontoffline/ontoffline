/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
public class ShopCartItemDiscountDto implements Serializable {


    @ApiModelProperty(value = "已选满减项", required = true)
    private ChooseDiscountItemDto chooseDiscountItemDto;

    @ApiModelProperty(value = "商品列表")
    private List<ShopCartItemDto> shopCartItems;

	public ChooseDiscountItemDto getChooseDiscountItemDto() {
		return chooseDiscountItemDto;
	}

	public void setChooseDiscountItemDto(ChooseDiscountItemDto chooseDiscountItemDto) {
		this.chooseDiscountItemDto = chooseDiscountItemDto;
	}

	public List<ShopCartItemDto> getShopCartItems() {
		return shopCartItems;
	}

	public void setShopCartItems(List<ShopCartItemDto> shopCartItems) {
		this.shopCartItems = shopCartItems;
	}
    
    
}