/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
public class ChangeShopCartParam {

    @ApiModelProperty(value = "购物车ID", required = true)
    private Long basketId;

    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    @NotNull(message = "skuId不能为空")
    @ApiModelProperty(value = "skuId", required = true)
    private Long skuId;

    @NotNull(message = "店铺ID不能为空")
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @NotNull(message = "商品个数不能为空")
    @ApiModelProperty(value = "商品个数", required = true)
    private Integer count;

    @ApiModelProperty(value = "分销推广人卡号")
    private String distributionCardNo;

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDistributionCardNo() {
		return distributionCardNo;
	}

	public void setDistributionCardNo(String distributionCardNo) {
		this.distributionCardNo = distributionCardNo;
	}
    
    
}
