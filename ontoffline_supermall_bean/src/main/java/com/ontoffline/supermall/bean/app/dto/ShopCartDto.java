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
public class ShopCartDto implements Serializable {

	@ApiModelProperty(value = "店铺ID", required = true)
	private Long shopId;

	@ApiModelProperty(value = "店铺名称", required = true)
	private String shopName;

	@ApiModelProperty(value = "购物车商品", required = true)
	private List<ShopCartItemDiscountDto> shopCartItemDiscounts;

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

	public List<ShopCartItemDiscountDto> getShopCartItemDiscounts() {
		return shopCartItemDiscounts;
	}

	public void setShopCartItemDiscounts(List<ShopCartItemDiscountDto> shopCartItemDiscounts) {
		this.shopCartItemDiscounts = shopCartItemDiscounts;
	}


}
