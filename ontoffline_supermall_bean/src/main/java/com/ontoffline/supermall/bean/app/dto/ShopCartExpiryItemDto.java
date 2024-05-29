/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel("购物车失效商品对象")
public class ShopCartExpiryItemDto {
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "商品项", required = true)
    private List<ShopCartItemDto> shopCartItemDtoList;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<ShopCartItemDto> getShopCartItemDtoList() {
		return shopCartItemDtoList;
	}

	public void setShopCartItemDtoList(List<ShopCartItemDto> shopCartItemDtoList) {
		this.shopCartItemDtoList = shopCartItemDtoList;
	}
    
    

}
