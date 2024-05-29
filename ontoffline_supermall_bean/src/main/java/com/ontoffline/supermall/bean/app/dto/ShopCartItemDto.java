/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ontoffline.supermall.common.serializer.json.ImgJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangylontoffline
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ShopCartItemDto extends ProductItemDto implements Serializable {
    private static final long serialVersionUID = -8284981156242930909L;

    @ApiModelProperty(value = "购物车ID", required = true)
    private Long basketId;

    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @ApiModelProperty(value = "规格名称", required = true)
    private String skuName;

    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "商品原价", required = true)
    private Double oriPrice;

    @ApiModelProperty(value = "推广员使用的推销卡号")
    private String distributionCardNo;

    @ApiModelProperty(value = "加入购物车的时间")
    private Date basketDate;

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public String getDistributionCardNo() {
		return distributionCardNo;
	}

	public void setDistributionCardNo(String distributionCardNo) {
		this.distributionCardNo = distributionCardNo;
	}

	public Date getBasketDate() {
		return basketDate;
	}

	public void setBasketDate(Date basketDate) {
		this.basketDate = basketDate;
	}
    
    
}
