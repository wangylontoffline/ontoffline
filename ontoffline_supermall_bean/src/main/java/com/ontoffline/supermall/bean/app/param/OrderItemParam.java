/*
 * Copyright (c) 2017-2099 南京奥托兰科技有限公司 All rights reserved.
 *
 * https://www.ontoffline.com
 *
 * 保留所有权力，翻版必究
 */

package com.ontoffline.supermall.bean.app.param;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangylontoffline
 */

@Data
@ApiModel(value= "购物车物品参数")
public class OrderItemParam {

	@NotNull(message = "产品ID不能为空")
	@ApiModelProperty(value = "产品ID",required=true)
	private Long prodId;
	
	@NotNull(message = "skuId不能为空")
	@ApiModelProperty(value = "skuId",required=true)
	private Long skuId;
	
	@NotNull(message = "产品数量不能为空")
	@Min(value = 1,message = "产品数量不能为空")
	@ApiModelProperty(value = "产品数量",required=true)
	private Integer prodCount;

	@NotNull(message = "店铺id不能为空")
	@ApiModelProperty(value = "店铺id",required=true)
	private Long shopId;

	@ApiModelProperty(value = "推广员使用的推销卡号")
	private String distributionCardNo;

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

	public Integer getProdCount() {
		return prodCount;
	}

	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getDistributionCardNo() {
		return distributionCardNo;
	}

	public void setDistributionCardNo(String distributionCardNo) {
		this.distributionCardNo = distributionCardNo;
	}
	
	
}
